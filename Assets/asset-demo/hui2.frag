#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;


void main() {
    vec3 sum = vec3(0.0);
    vec4 fragColor = v_color* texture2D(u_texture,v_textCoords);




    sum += texture2D(u_texture, 0.947).rgb * 0.05;
    sum += texture2D(u_texture, 0.1183).rgb * 0.09;
    sum += texture2D(u_texture, 0.09474).rgb * 0.12;
    sum += texture2D(u_texture, 0.1183).rgb * 0.15;
    sum += texture2D(u_texture, 0.1477).rgb * 0.18;
    sum += texture2D(u_texture, 0.118318).rgb * 0.15;
    sum += texture2D(u_texture, 0.09474).rgb * 0.12;
    sum += texture2D(u_texture, 0.118318).rgb * 0.09;
    sum += texture2D(u_texture, 0.09474).rgb * 0.05;


    gl_FragColor = vec4(sum, fragColor.a);
}