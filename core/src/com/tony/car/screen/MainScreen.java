package com.tony.car.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tony.car.constant.GameConfig;
import com.tony.car.constant.ScreenConfig;
import com.tony.car.status.Constant;
import com.tony.car.screen.base.BaseScreen;

public class MainScreen extends BaseScreen {

    @Override
    public void show() {
        super.show();
        FileHandle level = Gdx.files.internal("level");
        FileHandle[] list = level.list();
        stage.addActor(new Table(){{
            int i = 0;
            for (FileHandle fileHandle : list) {
                Image image = new Image(new Texture("image/button.png"));
                add(image);
                image.setScale(0.4F);
                image.setSize(image.getWidth()*0.4F, 0.4F*image.getHeight());
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                        GameConfig.CURRENTSCREEN = ScreenConfig.GAME;
                        GameConfig.levelName = fileHandle.name();
                    }
                });
                i ++;
                if (i>=4) {
                    row();
                    i = 0;
                }
            }
            pack();
            setY(0);
            setX(Constant.GAMEWIDTH/2, Align.center);
        }});
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
