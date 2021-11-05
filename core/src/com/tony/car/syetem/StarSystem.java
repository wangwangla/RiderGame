package com.tony.car.syetem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class StarSystem extends IteratingSystem {
    /**
     * 需要加入star
     */
    public StarSystem() {
        super(Family.all().get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        /*if (true){
          如果  xxx
        }*/
    }
}
