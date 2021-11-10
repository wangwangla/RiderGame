package com.tony.car.constant;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;

public interface CarConfig {
    //position
    float baseX = 2;
    float baseY = 10;
    //力
    float force = 50;
    //
    Vector2 carAngSpeed = new Vector2(1,0);
    Vector2 forceDown = new Vector2(120,0);
}
