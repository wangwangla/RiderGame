package com.tony.car.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;

public class WheelComponent implements Component {
    public float staus;
    public WheelJoint wheelJoint;

    public void setStaus(float staus) {
        this.staus = staus;
    }

    public void setWheelJoint(WheelJoint wheelJoint) {
        this.wheelJoint = wheelJoint;
    }

    public void touchDown() {
        wheelJoint.enableMotor(true);
        wheelJoint.setMotorSpeed(-100);
    }

    public void stop() {
        wheelJoint.enableMotor(false);
        wheelJoint.setMotorSpeed(0);
    }
}
