package com.tony.bricks.utils;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class MathUtils {
    public static Vector2 convertPosV(Vector2 pos,Vector2 center,float angle){
        Vector2 posTemp = new Vector2(pos);
        Vector2 centerTemp = new Vector2(center);
        posTemp.sub(centerTemp);
        posTemp.rotateRad((float) Math.toRadians(angle));
        centerTemp.add(posTemp);
        return posTemp;
    }

    public static void main(String[] args) {
        Vector2 po = new Vector2(243.0F,128.5F);
        Vector2 ce = new Vector2(0,0);
        Vector2 vector2 = MathUtils.convertPosV(po, ce, 0.0F);
    }

    public static float toDegrees(float angle) {

        return angle;
    }
}
