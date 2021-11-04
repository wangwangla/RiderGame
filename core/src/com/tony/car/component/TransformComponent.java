package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    public Vector2 position;
    public float angle;
    public Vector2 scale ;
    public TransformComponent(float x,float y,float angle){
        this.position = new Vector2(x,y);
        this.angle = angle;
        scale = new Vector2(1,1);
    }

    public TransformComponent(Vector2 position,float angle){
        this.position = new Vector2(position);
        this.angle = angle;
        scale = new Vector2(1,1);
    }
}
