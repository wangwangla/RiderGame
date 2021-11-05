package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;

public class WheelComponent implements Component {
    public float staus;
    public WheelJoint wheelJoint;

    public float speed  = 50;

    public void setStaus(float staus) {
        this.staus = staus;
    }

    public void setWheelJoint(WheelJoint wheelJoint) {
        this.wheelJoint = wheelJoint;
    }
}
