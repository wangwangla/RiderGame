package com.tony.car.builder;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.tony.car.component.CarComponent;
import com.tony.car.component.MoveComponent;
import com.tony.car.component.RoadComponent;
import com.tony.car.component.TextureComponent;
import com.tony.car.component.TransformComponent;
import com.tony.car.status.Constant;
import static com.tony.car.status.Constant.PPM;

public class WorldBuilder {
    private Engine engine;
    private float m_speed = -49*1.150F;
    private CarInstance carInstance;

    public WorldBuilder(Engine engine) {
        this.carInstance = new CarInstance(engine);
        this.engine = engine;
    }

    public void build(TiledMap tiledMap, World world, PhysicsShapeCache physicsBodies) {
        carInstance.createCar(world);
        initMap(tiledMap, world, physicsBodies);
    }

    private void initMap(TiledMap tiledMap, World world, PhysicsShapeCache physicsBodies) {
        MapLayers layers = tiledMap.getLayers();
        //路面
        for (MapLayer layer : layers) {
            for (MapObject object : layer.getObjects()) {
                TiledMapTileMapObject tiledMapTileMapObject = ((TiledMapTileMapObject) object);
                float x = tiledMapTileMapObject.getX();
                float y = tiledMapTileMapObject.getY();
                MapProperties properties1 = tiledMapTileMapObject.getProperties();
                float rotation = 0;
                if (properties1.containsKey("rotation")) {
                    rotation = -(float) properties1.get("rotation");

                }
                float width = (float) properties1.get("width");
                float height = (float) properties1.get("height");
                TextureRegion textureRegion = tiledMapTileMapObject.getTextureRegion();
                Vector2 pos = new Vector2();
                pos.set(width, height);
                FileTextureData textureData = (FileTextureData) textureRegion.getTexture().getTextureData();
                String[] split = textureData.getFileHandle().name().split("\\.");
                float scaleX = width / textureRegion.getRegionWidth();
                float scaleY = height / textureRegion.getRegionHeight();
                String name = split[0];
                textureRegion.setRegionWidth((int)width);
                textureRegion.setRegionHeight((int)height);
                if (tiledMapTileMapObject.isFlipVertically()) {
                    name = name + "_2";
                } else if (tiledMapTileMapObject.isFlipHorizontally()) {
                    name = name + "_1";
                }
                Body body = physicsBodies.createBody(name, world, scaleX * PPM, scaleY * PPM);
                body.setTransform(x * PPM, y * PPM, (float) Math.toRadians(rotation));
                Entity entity = new Entity();
//                根据状态  进行分类  （先不写）
                entity.add(new RoadComponent());
//              位置
                entity.add(new TransformComponent(x,y,rotation));
//              纹理
                entity.add(new TextureComponent(textureRegion));
//                刚体
                entity.add(new MoveComponent(body));
                entity.add(new CarComponent(carInstance.getCarBody()));
                engine.addEntity(entity);

                body.setUserData(entity);

                Image image = new Image(textureRegion);
                group.addActor(image);
            }
        }
    }

    public void setPosition(Camera camera) {
        Vector2 position = carInstance.getPosition();
//        if (group!=null) {
//            group.setPosition(-position.x * (1/PPM)+5*(1/PPM),0);
//        }
        camera.position.set(position.x + 1/2.0F, Constant.WORLDHIGHT/2 ,0);
        camera.update();
        carInstance.update(position);
    }

    private Group group = new Group();
    public void setGroup(Group group) {
        this.group = group;
    }
}