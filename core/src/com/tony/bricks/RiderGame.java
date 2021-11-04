package com.tony.bricks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.tony.bricks.constant.Constant;
import com.tony.bricks.screen.LoadingScreen;
import com.ui.ManagerUIEditor;
import com.ui.loader.ManagerUILoader;
import com.ui.plist.PlistAtlas;
import com.ui.plist.PlistAtlasLoader;

public class RiderGame extends Game {
    private Batch batch;
    public AssetManager assetManager;
    public ExtendViewport viewport;
    public ExtendViewport stageViewport;

    @Override
    public void create() {
        initInstance();
        setScreen(new LoadingScreen(this));
    }

    private void initInstance(){
        Gdx.input.setCatchBackKey(true);
        viewport = new ExtendViewport(Constant.WORLDWIDTH, Constant.WORLDHIGHT);
        stageViewport = new ExtendViewport(Constant.WIDTH,Constant.HIGHT);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new CpuPolygonSpriteBatch();
        assetManager = new AssetManager();
        assetManager.setLoader(ManagerUIEditor.class,new ManagerUILoader(assetManager.getFileHandleResolver()));
        assetManager.setLoader(PlistAtlas.class, new PlistAtlasLoader(assetManager.getFileHandleResolver()));
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
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5F, 0.5F, 0.5F, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        super.render();
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
    }
}
