package com.game.main;

import java.awt.*;

public class SnakeBody extends Element {
    public SnakeBody(int x, int y, Color color) {
        super(x, y);
        this.setColor(color);
        this.setEatable(false);
    }

    public SnakeBody(Color color) {
        this.setColor(color);
        this.setEatable(false);
    }
}
