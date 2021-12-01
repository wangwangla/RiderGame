package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class TransformComponent implements Component {
    public Vector2 position;
    public float angle =0;
    public Vector2 scale ;
    public Vector2 basePosition;
    public boolean center = false;
    public TransformComponent(float x,float y,float angle){
        this.position = new Vector2(x,y);
        this.basePosition = new Vector2(x,y);
        this.angle = angle;
        scale = new Vector2(1,1);
    }

    public TransformComponent(Vector2 position,float angle){
        this.position = new Vector2(position);
        this.basePosition = new Vector2(position);
        this.angle = angle;
        scale = new Vector2(1,1);
    }
}
