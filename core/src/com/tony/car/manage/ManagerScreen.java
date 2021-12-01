package com.tony.car.manage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tony.car.constant.GameConfig;
import com.tony.car.constant.ScreenConfig;
import com.tony.car.screen.GameScreen;
import com.tony.car.screen.LoadingScreen;
import com.tony.car.screen.MainScreen;

public class ManagerScreen {
    private int currentStatus = -1 ;
    private Screen currentScreen;
    float time = 0;

    public ManagerScreen(){

    }

    public void resize(int width, int height) {
        if (currentScreen != null) {
            currentScreen.resize(width,height);
        }
    }

    public void update() {
        if (currentStatus != GameConfig.CURRENTSCREEN) {
            if (GameConfig.CURRENTSCREEN == 0){
                currentScreen = new LoadingScreen();
            }else if (GameConfig.CURRENTSCREEN == ScreenConfig.MAIN){
                currentScreen = new MainScreen();
            }else if (GameConfig.CURRENTSCREEN == ScreenConfig.GAME){
                currentScreen = new GameScreen();
            }
            if (currentScreen!=null) {
                currentScreen.show();
                resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
                currentStatus = GameConfig.CURRENTSCREEN;
            }
        }
        if (currentScreen != null){
            currentScreen.render(Gdx.graphics.getDeltaTime());
        }
        if (currentStatus == 0) {
            if (time >= 1) {
                GameConfig.CURRENTSCREEN = ScreenConfig.MAIN;
            } else {
                time += Gdx.graphics.getDeltaTime();
            }
        }
    }
    public void dispose() {
        currentScreen.dispose();
    }
}
