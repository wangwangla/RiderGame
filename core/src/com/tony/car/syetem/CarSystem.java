package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.tony.car.component.WheelComponent;

public class CarSystem extends IteratingSystem {
    private final ComponentMapper<WheelComponent> carM = ComponentMapper.getFor(WheelComponent.class);

    public CarSystem() {
        super(Family.all(WheelComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        WheelComponent wheelComponent = carM.get(entity);
        if (Gdx.input.isTouched()){
            wheelComponent.touchDown();
        }else {
            wheelComponent.stop();
        }
    }
}
