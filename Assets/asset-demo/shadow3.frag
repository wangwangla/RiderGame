#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;




const float FREQ_RANGE = 128.0;
const float PI = 3.1415;
const float RADIUS = 0.5;
const float BRIGHTNESS = 0.15;
const float SPEED = 0.5;
//convert HSV to RGB
vec3 hsv2rgb(vec3 color){
    vec4 konvert = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 calc = abs(fract(color.xxx + konvert.xyz) * 6.0 - konvert.www);
    return color.z * mix(konvert.xxx, clamp(calc - konvert.xxx, 0.0, 1.0), color.y);
}


vec3 doLine(vec2 fragment, float radius, float x) {
	vec3 col = hsv2rgb(vec3(x * 0.23 + 1 * 0.12, 1.0, 1.0));

	float freq = abs(fragment.x * 0.5);

	col *= (1.0 / abs(fragment.y)) * BRIGHTNESS * 1;
	col = col * smoothstep(radius, radius * 1.8, abs(fragment.x));


	return col;
}
void main() {
    vec2 fragPos = vec2(v_textCoords.x,v_textCoords.y);
    vec4 tempColor = v_color* texture2D(u_texture,fragPos);



    vec3 color = vec3(0.0,0.0,0.0);
    float c = cos(1 * SPEED);
    float s = sin(1 * SPEED);
    vec2 rot = mat2(c,s,-s,c) * fragPos;
    color += doLine(rot, (RADIUS ), rot.x);









    gl_FragColor = tempColor * vec4(color,1.0);
}

