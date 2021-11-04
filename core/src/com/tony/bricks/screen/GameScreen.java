package com.tony.bricks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.tony.bricks.RiderGame;
import com.tony.bricks.WorldListener.WorldListener;
import com.tony.bricks.constant.Constant;
import com.tony.bricks.screen.base.BaseScreen;
import com.tony.bricks.worldBuilder.WorldBuilder;

public class GameScreen extends BaseScreen {
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private TiledMap tiledMap;
    private Stage stage;
    private PhysicsShapeCache physicsBodies;
    private RiderGame riderGame;
    private OrthographicCamera camera;
    private String name = "";
    WorldBuilder worldBuilder;

    public GameScreen(RiderGame riderGame){
        super(riderGame);
    }

    public GameScreen(RiderGame riderGame, String name){
        this(riderGame);
        this.name = name;
        this.riderGame = riderGame;
    }

    @Override
    public void show() {
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera = (OrthographicCamera) (riderGame.viewport.getCamera());
        camera.translate(Constant.worldWidth/2,Constant.worldHeight/2);
        camera.update();
        stage = new Stage(riderGame.stageViewport);
        world = new World(new Vector2(0,-12.3F), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("level/"+name);
        worldBuilder = new WorldBuilder();
        Group group = new Group();
        stage.addActor(group);
        physicsBodies = new PhysicsShapeCache("car.xml");
        worldBuilder.build(tiledMap,world,group,physicsBodies);
        stage.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT){
                    touchDown = true;
                }
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    touchDown = false;
                }
                return super.keyUp(event, keycode);
            }
        });

        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touchDown = true;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                touchDown = false;
            }
        });

        Gdx.input.setInputProcessor(stage);
        world.setContactListener(new WorldListener());

        Image image = new Image(new Texture("image/button.png"));
        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                riderGame.setScreen(new MainScreen(riderGame));
            }
        });
        image.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT, Align.top);
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        Constant.jiechu = false;
        world.step(1 / 60f, 8, 3);
        box2DDebugRenderer.render(world, camera.combined);
        stage.act();
        stage.draw();
        if (touchDown){
            worldBuilder.right();
        }else{
            worldBuilder.stop();
        }
        worldBuilder.setPosition(camera);
    }

    private boolean touchDown = false;

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

    }
}
