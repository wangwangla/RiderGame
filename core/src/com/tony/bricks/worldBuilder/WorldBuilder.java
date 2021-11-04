package com.tony.bricks.worldBuilder;

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
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.tony.bricks.constant.Constant;

import static com.tony.bricks.constant.Constant.PPM;
import static com.tony.bricks.constant.Constant.anSpeed;

public class WorldBuilder {
    private Group group;

    private float m_speed = -49*1.10F;
    private CarInstance carInstance;

    public WorldBuilder() {
        carInstance = new CarInstance();
    }

    public void build(TiledMap tiledMap, World world, Group stage, PhysicsShapeCache physicsBodies) {
        this.group = stage;
        carInstance.createCar(world,group);
        initMap(tiledMap, world, stage, physicsBodies);
    }


    private void initMap(TiledMap tiledMap, World world, Group stage, PhysicsShapeCache physicsBodies) {
        MapLayers layers = tiledMap.getLayers();
        for (MapLayer layer : layers) {
            for (MapObject object : layer.getObjects()) {
                TiledMapTileMapObject tiledMapTileMapObject = ((TiledMapTileMapObject) object);
                float x = tiledMapTileMapObject.getX();
                float y = tiledMapTileMapObject.getY();
                MapProperties properties1 = tiledMapTileMapObject.getProperties();
                float rotation = 0;
                if (properties1.containsKey("rotation")) {
                    rotation = -(float) properties1.get("rotation");
                    System.out.println(rotation);
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

                if (tiledMapTileMapObject.isFlipVertically()) {
                    name = name + "_2";
                } else if (tiledMapTileMapObject.isFlipHorizontally()) {
                    name = name + "_1";
                }
                Body body = physicsBodies.createBody(name, world, scaleX * PPM, scaleY * PPM);
                body.setTransform(x * PPM, y * PPM, (float) Math.toRadians(rotation));
                Image image = new Image(textureRegion);
                image.setSize(width, height);
                image.setPosition(x, y);
                stage.addActor(image);
                image.setRotation(rotation);
            }
        }
    }

    public void right(){
        if (!Constant.jiechu){
            carInstance.setAngularDamping(0);
            carInstance.setAngularVelocity(6);
            carInstance.applyLinearImpulse(anSpeed);
        }else{
            carInstance.setMotorSpeed(-m_speed);
            carInstance.setDownForce();
        }
    }

    public void stop() {
        carInstance.setAngularDamping(8);
        if (Constant.jiechu){
            carInstance.setLinearDamping(3);
        }else{
            carInstance.setLinearDamping(0);
        }
        carInstance.stop();
    }

    public void setPosition(Camera camera) {
        Vector2 position = carInstance.getPosition();
        if (group!=null) {
            group.setPosition(-position.x * (1/PPM)+5*(1/PPM),0);
        }
        camera.position.set(position.x + 1/2.0F, Constant.WORLDHIGHT/2-2,0);
        camera.update();
        carInstance.update();
    }
}