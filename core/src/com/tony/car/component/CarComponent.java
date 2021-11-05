package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class CarComponent implements Component {
    public Vector2 carAngSpeed = new Vector2(1,0);
    public Body carBody;
    public CarComponent(Body carBody){
        this.carBody =  carBody;
    }
}
