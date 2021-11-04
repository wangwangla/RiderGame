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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import com.tony.bricks.constant.Constant;

public class WorldBuilder1 {
    private Body m_wheel1,m_wheel2;
    private Body m_car;
    private RevoluteJoint m_spring1;
    private RevoluteJoint m_spring2;
    float carBaseY =9;
    float carBaseX =5;
    private Group group;
    public void build(TiledMap tiledMap, World world, Group stage, PhysicsShapeCache physicsBodies){
        this.group = stage;
        MapLayers layers = tiledMap.getLayers();
        for (MapLayer layer : layers) {
            for (MapObject object : layer.getObjects()) {
                TiledMapTileMapObject tiledMapTileMapObject = ((TiledMapTileMapObject) object);
                float x = tiledMapTileMapObject.getX();
                float y = tiledMapTileMapObject.getY();
                MapProperties properties1 = tiledMapTileMapObject.getProperties();
                float rotation = 0;
                if (properties1.containsKey("rotation")){
                    rotation = -(float)properties1.get("rotation");
                    System.out.println(rotation);
                }
                float width = (float)properties1.get("width");
                float height = (float)properties1.get("height");
                TextureRegion textureRegion = tiledMapTileMapObject.getTextureRegion();
                Vector2 pos = new Vector2();
                pos.set(width,height);
                FileTextureData textureData = (FileTextureData) textureRegion.getTexture().getTextureData();
                String[] split = textureData.getFileHandle().name().split("\\.");
                float scaleX = width / textureRegion.getRegionWidth();
                float scaleY = height / textureRegion.getRegionHeight();
                String name = split[0];

                if (tiledMapTileMapObject.isFlipVertically()){
                    name = name+"_2";
                }else if (tiledMapTileMapObject.isFlipHorizontally()){
                    name = name+"_1";
                }
                Body body = physicsBodies.createBody(name,world,scaleX * PPM,scaleY * PPM);
                if (body != null){

                }
                body.setTransform(x*PPM,y*PPM,(float)Math.toRadians(rotation));
                Image image = new Image(textureRegion);
                image.setSize(width,height);
                image.setPosition(x,y);
                stage.addActor(image);
                image.setRotation(rotation);
            }
        }
        car(world);


    }

    float baseX = 5;
    float baseY = 8;

    void car(World world){

        m_speed = 100.0f;

        {
            PolygonShape chassis = new PolygonShape();
            Vector2 vertices[] = new Vector2[6];
            //1
            vertices[0] = new Vector2(-1.0f, -0.6f);
            vertices[1] = new Vector2(1.5f, -0.6f);
            vertices[2] = new Vector2(1.2f, 0f);
            vertices[3] = new Vector2(0.0f, 0.3f);
            vertices[4] = new Vector2(-1.15f, 0.3f);
            vertices[5] = new Vector2(-1.2f, 0.2f);
            chassis.set(vertices);

            CircleShape circle = new CircleShape();
            circle.setRadius(0.3f);

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(baseX+0.0f,baseY+1.0f);
            m_car = world.createBody(bd);
            m_car.createFixture(chassis, 40.0f);

            FixtureDef fd = new FixtureDef();
            fd.shape = circle;
            fd.density = 2.0f;
            fd.friction = 0.9f;
            Filter filter = fd.filter;
            filter.groupIndex = 10;

            bd.position.set(baseX-0.6f, baseY+0.35f);
            m_wheel1 = world.createBody(bd);
            m_wheel1.createFixture(fd);

            bd.position.set(baseX+1.2f, baseY+0.4f);
            m_wheel2 = world.createBody(bd);
            m_wheel2.createFixture(fd);

            RevoluteJointDef jd = new RevoluteJointDef();
            Vector2 axis = new Vector2(0.0f, 1.3f);

            jd.initialize(m_car, m_wheel1, m_wheel1.getPosition());
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 90.0f;
            jd.enableMotor = true;
            m_spring1 = (RevoluteJoint) world.createJoint(jd);
            Vector2 position = m_wheel2.getPosition();
            jd.initialize(m_car, m_wheel2,position);
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 90.0f;
            jd.enableMotor = false;
            m_spring2 = (RevoluteJoint) world.createJoint(jd);

            m_car.setLinearDamping(1.2F);
        }
    }

    private float PPM =0.03F;
    float m_speed = 1000;
    Vector2 force = new Vector2(1200,0);
//    Vector2 force = new Vector2(100,0);

    public void left() {


    }

    float curentSpeed = 0;
    float maxspeed = 100;
    public void right(){
        curentSpeed+=1;
        if (curentSpeed>maxspeed) curentSpeed = maxspeed;
        m_car.setLinearVelocity(curentSpeed,0);
//        if (!Constant.jiechu) {
//
//            m_car.applyTorque(100, true);
////            m_spring1.enableMotor(true);
////            m_spring1.setMotorSpeed(-m_speed*3);
//        }
//        else{
//            m_spring1.enableMotor(true);
//            m_spring1.setMotorSpeed(-m_speed);
//        }
    }
    Vector2 z = new Vector2(0,0);
    public void stop() {
        m_spring1.enableMotor(false);
        m_car.applyTorque(0, true);
    }


    Vector2 down = new Vector2(0,0);

    public void setPosition(Camera camera) {
        Vector2 position = m_car.getPosition();
        if (group!=null) {
            group.setPosition(-position.x * (1/0.03F)+5*(1/0.03F),position.y);
        }
        float angle = m_car.getAngle();
        float a = (float)Math.toDegrees(angle);
        camera.position.set(position.x+5.84F,position.y,0);
        camera.update();
        down.set(0,-500);
        down.setAngle(a-90);
        if (Constant.jiechu) {
            isStart = true;
            m_car.applyForceToCenter(down, true);
        }else{
        }
    }

    private boolean isStart;
}
