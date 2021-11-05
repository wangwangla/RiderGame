package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tony.car.component.CameraComponent;
import com.tony.car.component.CarComponent;
import com.tony.car.constant.Constant;

/**
 * 相机跟着车移动
 */
public class CamraSystem extends IteratingSystem {
    private ComponentMapper<CameraComponent> cameraM = ComponentMapper.getFor(CameraComponent.class);
    private ComponentMapper<CarComponent> carM = ComponentMapper.getFor(CarComponent.class);
    public CamraSystem() {
        super(Family.all(CameraComponent.class,CarComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        CarComponent carComponent = carM.get(entity);
        CameraComponent cameraComponent = cameraM.get(entity);
        cameraComponent.camera.position.set(
                carComponent.carBody.getPosition().x + 1/2.0F,
                Constant.WORLDHIGHT/2 ,0);
        cameraComponent.camera.update();
    }
}
