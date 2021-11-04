package com.tony.bricks.WorldListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tony.bricks.constant.Constant;

public class WorldListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        short groupIndex1 = contact.getFixtureA().getFilterData().groupIndex;
        short groupIndex2 = contact.getFixtureB().getFilterData().groupIndex;
//        撞击的是块是什么方向来 确定参数
//        Constant.anSpeed = 1;
        if (groupIndex1 == 10){
            if (groupIndex2 == 0){
                Constant.jiechu = true;
//                Constant.anSpeed = 1;
            }
        }
        if (groupIndex1 == 0){
            if (groupIndex2 == 10){
                Constant.jiechu = true;
//                Constant.anSpeed = 1;
            }
        }
    }
}
