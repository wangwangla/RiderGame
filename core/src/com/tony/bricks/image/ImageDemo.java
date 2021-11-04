package com.tony.bricks.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDemo extends Group {
    private ShaderProgram program;
    public ImageDemo(){
        program = new ShaderProgram(Gdx.files.internal("shadow.vert"),
                Gdx.files.internal("shadow.frag"));
        Image image = new Image(new Texture("ddd.png"));
        image.setY(100);
        addActor(image);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.flush();
        batch.setShader(program);
        super.draw(batch, parentAlpha);
        batch.flush();
        batch.setShader(null);
    }
}
