package com.tony.car.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.tony.car.CarGame;
import com.tony.car.builder.WorldBuilder;
import com.tony.car.constant.GameConfig;
import com.tony.car.status.Constant;
import com.tony.car.screen.base.BaseScreen;
import com.tony.car.syetem.CamraSystem;
import com.tony.car.syetem.CarSystem;
import com.tony.car.syetem.MoveSystem;

import com.tony.car.syetem.RenderSystem;
import com.tony.car.worldListener.WorldListener;

public class GameScreen extends BaseScreen {
    private World world;
    private Box2DDebugRenderer render;
    private Engine engine;
    private Group group;

    public GameScreen(){
        world = new World(new Vector2(0,-9),true);
        render = new Box2DDebugRenderer();
    }
    private OrthographicCamera camera;
    @Override
    public void show() {
        super.show();
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
//        TiledMap tiledMap = tmxMapLoader.load("level/level-A.tmx");
        TiledMap tiledMap = tmxMapLoader.load("level/"+ GameConfig.levelName);
        PhysicsShapeCache physicsBodies = new PhysicsShapeCache("car.xml");
        camera = (OrthographicCamera) (CarGame.viewport.getCamera());
        camera.translate(Constant.worldWidth/2,Constant.worldHeight/2);
        camera.update();
        Constant.camera = camera;
        engine = new Engine();
        engine.addSystem(new CarSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new RenderSystem());
        engine.addSystem(new CamraSystem());
        WorldBuilder builder = new WorldBuilder(engine);
        group = new Group();
        builder.setGroup(group);
        builder.build(tiledMap,world,physicsBodies);
        world.setContactListener(new WorldListener());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        world.step(1/60F,8,3);
        render.render(world, camera.combined);
        engine.update(delta);
        group.act(Gdx.graphics.getDeltaTime());
        group.draw(CarGame.batch,1);
    }
}
