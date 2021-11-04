package com.tony.car;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.tony.car.constant.Constant;
import com.tony.car.manage.ManagerScreen;
import com.tony.car.screen.LoadingScreen;

public class CarGame extends Game {
    public static Batch batch;
    public AssetManager assetManager;
    public static  ExtendViewport viewport;
    public static ExtendViewport stageViewport;
    private ManagerScreen managerScreen;

    @Override
    public void create() {
        initInstance();
        managerScreen = new ManagerScreen();
        managerScreen.setStatus(0);
    }

    private void initInstance(){
        Gdx.input.setCatchBackKey(true);
        viewport = new ExtendViewport(Constant.WORLDWIDTH, Constant.WORLDHIGHT);
        stageViewport = new ExtendViewport(Constant.WIDTH,Constant.HIGHT);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new CpuPolygonSpriteBatch();
        assetManager = new AssetManager();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        viewport.update(width,height);
        viewport.apply();
        Constant.worldWidth = viewport.getWorldWidth();
        Constant.worldHeight = viewport.getWorldHeight();
        stageViewport.update(width,height);
        stageViewport.apply();
        Constant.GAMEWIDTH = stageViewport.getWorldWidth();
        Constant.GAMEHIGHT = stageViewport.getWorldHeight();
        if (managerScreen!=null) {
            managerScreen.resize(width, height);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5F, 0.5F, 0.5F, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        managerScreen.update();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
        assetManager = null;
        if (batch!=null) {
            batch.dispose();
            batch = null;
        }
        managerScreen.dispose();
    }

    public ExtendViewport getStageViewport() {
        return stageViewport;
    }
}
