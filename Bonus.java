package com.game.main;

import com.game.common.Configure;

public class Bonus extends Element {
    public Bonus() {
        this.setColor(Configure.Bonus.color);
        this.setScore(Configure.Bonus.score);
        this.setEatable(true);
        // Bouns Food can be eat
    }
}
