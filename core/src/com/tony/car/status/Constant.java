package com.tony.car.status;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Constant {
    /**
     * screen size
     */
    public static final float WORLDWIDTH = 72*0.15F;
    public static final float WORLDHIGHT = 128*0.15F;
    public static final float WIDTH = 720;
    public static final float HIGHT = 1280;
    public static float worldHeight = 1280F;
    public static float worldWidth = 720F;
    public static float GAMEWIDTH = 720;
    public static float GAMEHIGHT = 1280;

    /**
     * game status
     */
    public static final int LOADING = 0;
    public static final int MAIN = 1;
    public static final int GAME = 2;

    /**
     * world arg
     */
    public static float offSetY = 0;
    public static final float PPM =0.015F;
    public static float anSpeed = 0.01F;

    /**
     * crame
     */
    public static OrthographicCamera camera;
    public static boolean jiechu = false;

}
