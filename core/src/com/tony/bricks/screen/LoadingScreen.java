package com.tony.bricks.screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tony.bricks.RiderGame;
import com.tony.bricks.constant.Constant;
import com.tony.bricks.screen.base.BaseScreen;

public class LoadingScreen extends BaseScreen {
    public LoadingScreen(RiderGame riderGame) {
        super(riderGame);
    }

    @Override
    public void show() {
        super.show();
        Image image = new Image(new Texture("Splash.jpg"));
        addActor(image);
        image.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        addAction(Actions.delay(1,Actions.run(()->{
            setScreen(new MainScreen(getRiderGame()));
        })));
    }

}
