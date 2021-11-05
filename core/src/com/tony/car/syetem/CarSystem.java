package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.tony.car.component.CarComponent;
import com.tony.car.component.WheelComponent;
import com.tony.car.constant.CarConfig;
import com.tony.car.constant.Constant;

/**
 * 车系统  控制小车运动
 */
public class CarSystem extends IteratingSystem {
    private final ComponentMapper<WheelComponent> wheelM = ComponentMapper.getFor(WheelComponent.class);
    private final ComponentMapper<CarComponent> carM = ComponentMapper.getFor(CarComponent.class);

    public CarSystem() {
        super(Family.all(WheelComponent.class,CarComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        WheelComponent wheelComponent = wheelM.get(entity);
        CarComponent carComponent = carM.get(entity);
        if (Gdx.input.isTouched()){
            if (Constant.jiechu){
                right(wheelComponent);
            }else {
                rotation(carComponent);
            }
            setDownForce(carComponent);
        }else {
            stop(wheelComponent);
        }
        Constant.jiechu = false;
    }

    public void right(WheelComponent wheelComponent) {
        wheelComponent.wheelJoint.enableMotor(true);
        wheelComponent.wheelJoint.setMotorSpeed(-wheelComponent.speed);
    }

    public void setDownForce(CarComponent carComponent) {
        float angle = carComponent.carBody.getAngle();
        float v = (float)Math.toDegrees(angle);
        if (Constant.jiechu) {
            CarConfig.forceDown.setAngle(v - 90.0F);
            carComponent.carBody.applyForceToCenter(CarConfig.forceDown, true);
        }
    }

    public void stop(WheelComponent wheelComponent) {
        wheelComponent.wheelJoint.enableMotor(false);
        wheelComponent.wheelJoint.setMotorSpeed(0);
    }

    public void rotation(CarComponent carComponent){
        Body carBody = carComponent.carBody;
        carBody.setAngularDamping(0);
        carBody.setAngularVelocity(8);
        carBody.applyLinearImpulse(carComponent.carAngSpeed.set(Constant.anSpeed, 0).scl(carBody.getMass()),carBody.getWorldCenter(), true);
    }
}
