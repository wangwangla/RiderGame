package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tony.car.component.MoveComponent;
import com.tony.car.component.TransformComponent;

/**
 * 移动系统  给
 */
public class MoveSystem extends IteratingSystem {
    private ComponentMapper<MoveComponent> movementM = ComponentMapper.getFor(MoveComponent.class);
    private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);

    public MoveSystem() {
        super(Family.all(MoveComponent.class,TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        MoveComponent movement = movementM.get(entity);
        TransformComponent transform = transformM.get(entity);
        transform.position.set(movement.body.getPosition());
    }
}
