package com.game.main;

import com.game.common.Direction;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakeBody> bodies;
    private Direction direction;
    private int score = 0;
    private boolean isAlive;
    private Color bodyColor;

    public Snake(int x, int y, Color bodyColor) {
        this.direction = Direction.UP;
        this.isAlive = true;
        this.bodyColor = bodyColor;
        initBody(x, y);
    }

    private void initBody(int x, int y) {
        LinkedList<SnakeBody> bodies = new LinkedList<>();
        bodies.add(new SnakeBody(x, y, getBodyColor()));
        this.bodies = bodies;
    }
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public LinkedList<SnakeBody> getBodies() {
        return bodies;
    }

    public int getScore() {
        return score;
    }

    public void eatElement(Element element) {
        if (getBodies().size() == 0){
            setAlive(false);
            return;
        }
        if (element == null || element.isEatable()){
            SnakeBody nextHead = new SnakeBody( nextX(), nextY(), getBodyColor());
            if (element == null) {
                this.bodies.addFirst(nextHead);
                this.bodies.removeLast();
            } else {
                if (element.getScore() >= 1) {
                    for (int i = 0; i < element.getScore(); i++){
                        this.bodies.addFirst(nextHead);
                        nextHead = new SnakeBody( nextX(), nextY(), getBodyColor());
                    }
                } else {
                    this.bodies.removeLast();
                }
                this.score = this.score + element.getScore();
            }
        } else {
            setAlive(false);
        }
    }

    public int nextX() {
        int x = this.bodies.getFirst().getX();
        switch (this.direction) {
            case LEFT: x = x -1; break;
            case RIGHT: x = x + 1; break;
        }
        return x;
    }

    public int nextY() {
        int y = this.bodies.getFirst().getY();
        switch (this.direction) {
            case UP: y = y -1; break;
            case DOWN: y = y + 1; break;
        }
        return y;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }
}
