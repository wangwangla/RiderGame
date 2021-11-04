package com.tony.car.syetem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.tony.car.CarGame;
import com.tony.car.component.TextureComponent;
import com.tony.car.component.TransformComponent;



public class RenderSystem extends IteratingSystem {
    private final Array<Entity> renderArray;
    private Batch batch;
    private final ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<TextureComponent> rendererM = ComponentMapper.getFor(TextureComponent.class);


    public RenderSystem() {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());
        this.batch = CarGame.batch;
        renderArray = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.begin();
        for (Entity entity : renderArray) {
            TransformComponent transform = transformM.get(entity);
            TextureComponent tex = rendererM.get(entity);
            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;
//            batch.draw(tex.region,
//                    transform.position.x - originX, transform.position.y - originY,
//                    originX, originY,
//                    width, height,
//                    transform.scale.x, transform.scale.y,
//                    transform.angle);
        }

        batch.end();
        renderArray.clear();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        renderArray.add(entity);
    }
}