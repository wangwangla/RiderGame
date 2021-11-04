package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

public class CarComponent implements Component {
    public Body carBody;
    public CarComponent(Body carBody){
        this.carBody =  carBody;
    }
}
