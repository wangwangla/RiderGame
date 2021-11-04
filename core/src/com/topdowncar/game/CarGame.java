package com.topdowncar.game;

import com.badlogic.gdx.Game;
import com.topdowncar.game.screens.PlayScreen;

/**
 * 这个游戏，只使用到了滑动摩擦力，两个驱动轮子，实际也不会能称为是轮子，他和地面进行摩擦
 * 运动是在轮子上加力
 */
public class CarGame extends Game {
    @Override
    public void create() {
        setScreen(new PlayScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
