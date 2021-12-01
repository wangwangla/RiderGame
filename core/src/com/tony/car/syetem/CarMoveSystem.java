package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.tony.car.component.CarComponent;
import com.tony.car.component.TextureComponent;
import com.tony.car.component.TransformComponent;
import com.tony.car.component.WheelComponent;

public class CarMoveSystem  extends IteratingSystem {
    private ComponentMapper<TextureComponent> carTextureM = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<CarComponent> carM = ComponentMapper.getFor(CarComponent.class);
    private ComponentMapper<TransformComponent> transM = ComponentMapper.getFor(TransformComponent.class);

    public CarMoveSystem() {
        super(Family.all(TextureComponent.class, WheelComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        CarComponent carComponent = carM.get(entity);
        Vector2 position = carComponent.carBody.getPosition();
        TransformComponent transformComponent = transM.get(entity);
        TextureComponent textureComponent = carTextureM.get(entity);
        transformComponent.position.set(
                position.x* 66.66666666666667F - textureComponent.region.getRegionWidth()/2,
                position.y*66.66666666666667F- textureComponent.region.getRegionHeight()/2);
        transformComponent.basePosition.set(
                position.x* 66.66666666666667F- textureComponent.region.getRegionWidth()/2,
                position.y* 66.66666666666667F- textureComponent.region.getRegionHeight()/2);
        transformComponent.center = true;
        transformComponent.angle =(float) Math.toDegrees(carComponent.carBody.getAngle());
        System.out.println(
                transformComponent.position.x+"------------"+
                transformComponent.position.y+"------------"
        );
    }
}
