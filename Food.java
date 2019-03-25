package com.game.main;

import com.game.common.Configure;

public class Food extends Element {
    public Food() {
        this.setColor(Configure.Food.color);
        this.setScore(Configure.Food.score);
        this.setEatable(true);
        // Food can be eat
    }
}
