package com.game.listener;

import com.game.common.Direction;
import com.game.main.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player2KeyListener implements KeyListener {
    private Snake snake;
    public Player2KeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_S:
                snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_A:
                snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
                snake.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
