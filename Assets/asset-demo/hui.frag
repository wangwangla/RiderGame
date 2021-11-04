#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;


void main() {
    float block = 100.0;
    float delta = 1.0/block;
    vec4 maxColor = vec4(-1.0);
    float r = 0;
    float g = 0;
    float b = 0;
    for (int i = -1; i <= 1 ; i++) {
        for (int j = -1; j <= 1; j++) {
            float x = v_textCoords.x + float(i) * delta;
            float y = v_textCoords.y + float(i) * delta;
            maxColor = max((v_color* texture2D(u_texture,vec2(x,y))), maxColor);
            r+=maxColor.r;
            g+=maxColor.g;
            b+=maxColor.b;
        }
    }
    maxColor.r = r / 4;
    maxColor.g = g / 4;
    maxColor.b = b / 4;



    gl_FragColor = maxColor;
}