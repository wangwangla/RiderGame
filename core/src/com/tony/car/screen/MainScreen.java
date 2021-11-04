package com.tony.car.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tony.car.screen.base.BaseScreen;

public class MainScreen extends BaseScreen {
    private World world;
    private Box2DDebugRenderer render;
    public MainScreen(){

    }

    @Override
    public void show() {
        super.show();
        Image image = new Image(new Texture("cardemo.png"));
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
