package com.tony.car.manage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tony.car.screen.GameScreen;
import com.tony.car.screen.LoadingScreen;
import com.tony.car.screen.MainScreen;

public class ManagerScreen {
    private int currentStatus ;
    private int status;
    private Screen currentScreen;
    float time = 0;

    public ManagerScreen(){
        if (currentScreen == null){
            currentScreen = new LoadingScreen();
            currentScreen.show();
        }
    }

    public void resize(int width, int height) {
        if (currentScreen != null) {
            currentScreen.resize(width,height);
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void update() {
        if (currentStatus != status) {
            currentScreen = new GameScreen();
            currentScreen.show();
            currentStatus = status;
        }
        if (currentScreen != null){
            currentScreen.render(Gdx.graphics.getDeltaTime());
        }
        if (currentStatus == 0) {
            if (time >= 1) {
                setStatus(1);
            } else {
                time += Gdx.graphics.getDeltaTime();
            }
        }
    }
    public void dispose() {
        currentScreen.dispose();
    }
}
