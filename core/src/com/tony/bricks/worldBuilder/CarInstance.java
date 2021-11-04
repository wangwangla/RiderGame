package com.tony.bricks.worldBuilder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.tony.bricks.constant.Constant;

import static com.tony.bricks.constant.Constant.PPM;

public class CarInstance {
    private Body rearWheelBody;
    private Body carBody;
    private Image carTexture;
    //position
    float baseX = 2;
    float baseY = 5;
    //力
    float force = 50;
    //
    Vector2 carAngSpeed = new Vector2(0,0);
    private WheelJoint m_spring1;
    Vector2 forceDown = new Vector2(120,0);

    public void createCar(World world,Group group){
        float m_hz =15.0f;
        float m_zeta = 0.7f;
        float d = 0.1F;
        PolygonShape chassis = new PolygonShape();
        Vector2 vertices[] = new Vector2[7];
        vertices[0] = new Vector2(-1.1f/2, 0.3f/2-d);
        vertices[1] = new Vector2(0.f/2, 0.3f/2-d);
        vertices[2] = new Vector2(1.0f/2, 0.3f/2-d);
        vertices[3] = new Vector2(1.f/2, 0.6f/2-d);
        vertices[4] = new Vector2(0.0f/2, 0.6f/2-d);
        vertices[5] = new Vector2(-1.f/2, 0.6f/2-d);
        vertices[6] = new Vector2(-1.f/2, 0.6f/2-d);
        chassis.set(vertices);
        CircleShape circle = new CircleShape();
        circle.setRadius(0.16f);
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(baseX+0.0f,baseY+0.4f);
        carBody = world.createBody(bd);
        carBody.createFixture(chassis, 8.85f);
        MassData massData = carBody.getMassData();
        massData.center.y = -10.0F;
        FixtureDef fd = new FixtureDef();
        fd.shape = circle;
        fd.density = 39f;
        fd.friction = 1F;
        Filter filter = fd.filter;
        filter.groupIndex = 10;
        bd.position.set(baseX-0.7f/2, baseY+0.35f);
        rearWheelBody = world.createBody(bd);
        rearWheelBody.createFixture(fd);

        bd.position.set(baseX+0.7f/2, baseY+0.4f);
        Body m_wheel2 = world.createBody(bd);
        m_wheel2.createFixture(fd);
        WheelJointDef jd = new WheelJointDef();
        Vector2 axis = new Vector2(0.0f, 1.0f);

        jd.initialize(carBody, rearWheelBody, rearWheelBody.getPosition(), axis);
        jd.motorSpeed = 0.0f;
        jd.maxMotorTorque = force;
        jd.enableMotor = true;
        jd.frequencyHz = m_hz;
        jd.dampingRatio = m_zeta;
        m_spring1 = (WheelJoint) world.createJoint(jd);

        jd.initialize(carBody, m_wheel2, m_wheel2.getPosition(), axis);
        jd.motorSpeed = 0.0f;
        jd.maxMotorTorque = force;
        jd.enableMotor = false;
        jd.frequencyHz = m_hz;
        jd.dampingRatio = m_zeta;
        world.createJoint(jd);
        carTexture = new Image(new Texture("cardemo.png"));
        group.addActor(carTexture);
    }

    public void setAngularDamping(int angularDamping) {
        carBody.setAngularDamping(angularDamping);
    }

    public void setAngularVelocity(int angularDamping) {
        carBody.setAngularVelocity(angularDamping);
    }

    /*
    * 在某一点上施加冲动。这会立即修改速度。它还修改了角速度，如果
    *应用程序不在质量中心。这唤醒了身体。
    * */
    public void applyLinearImpulse(float speed) {
        float angle = carBody.getAngle();
        float v = (float)Math.toDegrees(angle);
        carBody.applyLinearImpulse(carAngSpeed.set(speed, 0).scl(carBody.getMass()),carBody.getWorldCenter(), true);
    }

    public void setMotorSpeed(float speed) {
        m_spring1.enableMotor(true);
        m_spring1.setMotorSpeed(-speed);
    }


    public void setDownForce() {
        float angle = carBody.getAngle();
        float v = (float)Math.toDegrees(angle);
        if (Constant.jiechu) {
            forceDown.setAngle(v - 90.0F);
            carBody.applyForceToCenter(forceDown, true);
        }
    }

    public void stop() {
        setAngularVelocity(0);
        m_spring1.enableMotor(false);
    }

    public void setLinearDamping(int linearDamping) {
        carBody.setLinearDamping(linearDamping);
    }

    public float getAngle() {
        return carBody.getAngle();
    }

    public Vector2 getPosition() {
        return carBody.getPosition();
    }

    public void update() {
        float angle = getAngle();
        Vector2 position = carBody.getPosition();
        carTexture.setOrigin(Align.center);
        carTexture.setPosition(position.x*(1/PPM),position.y*(1/PPM), Align.center);
        carTexture.setRotation((float)Math.toDegrees(angle));
    }
}
