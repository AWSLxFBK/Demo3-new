package com.game.main;

import com.game.common.Configure;

public class Poison extends Element {
    public Poison() {
        this.setColor(Configure.Poison.color);
        this.setScore(Configure.Poison.score);
        this.setEatable(true);
    }
}
