package com.tony.bricks.worldBuilder;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codeandweb.physicseditor.PhysicsShapeCache;

public class WorldBuilder2 {
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
    Image xxx ;

    void car(World world){
         {
            PolygonShape chassis = new PolygonShape();
            Vector2 vertices[] = new Vector2[4];
            vertices[0] = new Vector2(-1, -0.6f);
            vertices[1] = new Vector2(1, -0.6f);
            vertices[2] = new Vector2(1.2f, 0f);
            vertices[3] = new Vector2(0.0f, 0.3f);
            chassis.set(vertices);

            CircleShape circle = new CircleShape();
            circle.setRadius(0.3f);

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.DynamicBody;
            bd.position.set(baseX+0.0f,baseY+1.0f);
            m_car = world.createBody(bd);
            m_car.createFixture(chassis, 4.5f);

            FixtureDef fd = new FixtureDef();
            fd.shape = circle;
            fd.density = 2.0f;
            fd.friction = 0.9f;
            Filter filter = fd.filter;
            filter.groupIndex = 10;

            xxx = new Image(new Texture("Tiled_Assets/pickup_currency.png"));
            group.addActor(xxx);
        }
    }

    private float PPM =0.03F;
    float m_speed = 6F;
    Vector2 force = new Vector2(1200,0);
    Vector2 spe = new Vector2();

    public void right(){
        m_car.applyLinearImpulse(spe.set(m_speed,0 ).scl(m_car.getMass()), m_car.getWorldCenter(), true);
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

        xxx.setPosition(position.x*(1/0.03F),position.y*(1/0.03F));
    }

    private boolean isStart;

    public void left() {

    }

    public void stop() {

    }
}
