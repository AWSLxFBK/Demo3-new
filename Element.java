package com.game.main;

import java.awt.*;

public class Element extends Coordinate {
    private int score;
    private Color color;
    private boolean isEatable;

    public Element(int x, int y) {
        super(x, y);
    }

    public Element(){}

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }
}
