package com.tony.car.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.tony.car.constant.Constant;
import com.tony.car.screen.base.BaseScreen;

public class LoadingScreen extends BaseScreen {
    public LoadingScreen() {
    }

    @Override
    public void show() {
        super.show();
        Image image = new Image(new Texture("Splash.jpg"));
        image.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2, Align.center);
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
