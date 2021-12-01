package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tony.car.component.CarComponent;
import com.tony.car.component.MoveComponent;
import com.tony.car.component.TransformComponent;
import com.tony.car.status.Constant;

/**
 * 移动系统  给
 */
public class MoveSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<CarComponent> carMapper = ComponentMapper.getFor(CarComponent.class);
    public MoveSystem() {
        super(Family.all(MoveComponent.class,TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        TransformComponent transform = transformM.get(entity);
        CarComponent carComponent = carMapper.get(entity);
        transform.position.x = transform.basePosition.x - carComponent.carBody.getPosition().x * (1/Constant.PPM) + Constant.GAMEWIDTH / 2;
    }
}
