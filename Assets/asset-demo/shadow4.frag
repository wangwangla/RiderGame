#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;

void main() {
    vec2 uv = vec2(v_textCoords.x,v_textCoords.y);
    vec4 tempColor = v_color* texture2D(u_texture,uv);



    float s1 = 0.5+0.5*sin(iTime+uv.x*3.1415*(sin(iTime)+4.0));
    float s2 = 0.5+0.25*sin(iTime+uv.x*3.1415*(sin(iTime)*2.0+2.0));
    float r = pow(1.0-sqrt( abs(uv.y-s1)),1.5 );
    float g = pow(1.0-sqrt( abs(uv.y-s2)),1.5 );
    float b = 1.5*(r+g);



    gl_FragColor = tempColor * vec4(r,g,b,1.0);
}

