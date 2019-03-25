package com.game.panel;

import com.game.common.Configure;
import com.game.listener.Player2KeyListener;
import com.game.main.Element;
import com.game.main.Snake;
import com.game.main.SnakeBody;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TwoPlayerPanel extends PlayerPanel {
    private volatile Snake snake2;

    public TwoPlayerPanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!snake2.isAlive())
            dead2(g);
        if (!getSnake().isAlive() && !snake2.isAlive()){
            gameover(g);
        }
    }

    @Override
    public void initSnake(){
        super.initSnake();
        initSnake2();
    }

    private void initSnake2() {
        SnakeBody tempSnakeBody = new SnakeBody(Configure.SnakeBody.color2);
        do {
            setRandomPosition(tempSnakeBody);
        } while (isExistElement(tempSnakeBody));
        this.snake2 = new Snake(tempSnakeBody.getX(), tempSnakeBody.getY(), Configure.SnakeBody.color2);
        snake2.setBodyColor(Configure.SnakeBody.color2);
    }

    @Override
    public ArrayList<Element> getAllElement() {
        ArrayList<Element> elements = super.getAllElement();
        elements.addAll(snake2.getBodies());
        return elements;
    }

    @Override
    public void renderScore(Graphics g) {
        super.renderScore(g);
        g.setColor(snake2.getBodyColor());
        g.drawString("Score: " + snake2.getScore(), Configure.Element.ElementWidth * (Configure.Board.XNum + 10),
                Configure.Element.ElementWidth * 3);
    }

    @Override
    public void run() {
        super.run();
        JPanel thisjpanel = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                thisjpanel.addKeyListener(new Player2KeyListener(snake2));
                thisjpanel.setFocusable(true);
                thisjpanel.requestFocusInWindow();
                runSnake(snake2);
            }
        }).start();
    }

    public void dead2(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Dead!", Configure.Element.ElementWidth * (Configure.Board.XNum + 15),
                Configure.Element.ElementWidth * 3);
    }

    public void gameover(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over!", Configure.Element.ElementWidth * (Configure.Board.XNum + 13),
                Configure.Element.ElementWidth * 8);
    }
}
