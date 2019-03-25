package com.game.main;

import com.game.common.Configure;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
        this.setColor(Configure.Wall.color);
        this.setEatable(false);
    }

    public Wall() {
        this.setColor(Configure.Wall.color);
        this.setEatable(false);
    }
}
