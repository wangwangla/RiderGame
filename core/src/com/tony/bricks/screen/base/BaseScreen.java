package com.tony.bricks.screen.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tony.bricks.RiderGame;

public class BaseScreen implements Screen {
    private Stage stage;
    private RiderGame riderGame;
    public BaseScreen(RiderGame riderGame){
        this.riderGame = riderGame;
    }

    @Override
    public void show() {
        stage = new Stage(riderGame.stageViewport);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public RiderGame getRiderGame() {
        return riderGame;
    }

    public void addActor(Actor addActor){
        stage.addActor(addActor);
    }

    public void setScreen(BaseScreen screen) {
        riderGame.setScreen(screen);
    }

    public Stage getStage() {
        return stage;
    }

    public void addAction(Action action){
        stage.addAction(action);
    }
}

