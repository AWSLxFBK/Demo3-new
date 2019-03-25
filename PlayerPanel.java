package com.game.panel;

import com.game.common.Configure;
import com.game.listener.Player1KeyListener;
import com.game.main.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private ArrayList<Wall> wallList;
    private ArrayList<Wall> rampart;
    private Food food;
    private Poison poison;
    private Bonus bonus;

    private volatile Snake snake;

    public PlayerPanel() {
        initGame();
        initSnake();
        initFood();
        initBonus();
        initPoison();
        initWall();
        run();
    }

    public void initGame() {
        this.rampart = new ArrayList<>();
        for (int y = 0; y < Configure.Board.YNum; y++ ) {
            rampart.add(new Wall(0, y));
            rampart.add(new Wall(Configure.Board.XNum, y));
        }

        for (int x = 0; x <= Configure.Board.XNum; x++ ) {
            rampart.add(new Wall(x, 0));
            rampart.add(new Wall(x, Configure.Board.YNum));
        }
    }

    public void setRandomPosition(Element element) {
        int x = (int) (Math.random() * Configure.Board.XNum);
        int y = (int) (Math.random() * Configure.Board.YNum);
        element.setX(x);
        element.setY(y);
    }

    void fillRect(Graphics g, Element e) {
        int width = Configure.Element.ElementWidth;
        g.setColor(e.getColor());
        if (e instanceof SnakeBody) {
            g.fillOval(e.getX() * width, e.getY() * width, width, width);
        } else {
            g.fillRect(e.getX() * width, e.getY() * width, width, width);
        }
    }

    public ArrayList<Element> getBlockElement() {
        ArrayList<Element> elements = new ArrayList<Element>();
        if (getPoison() != null)
            elements.add(getPoison());
        if (getFood() != null)
            elements.add(getFood());
        if (getBonus() != null)
        	elements.add(getBonus());
        if (getWallList() != null)
            elements.addAll(getWallList());
        if (getRampart() != null)
            elements.addAll(getRampart());
        return elements;
    }

    private void initFood() {
        Food food = new Food();
        do {
            setRandomPosition(food);
        } while (isExistElement(food));
        setFood(food);
    }
    
    private void initBonus() {
    	Bonus bonus = new Bonus();
    	do {
    		setRandomPosition(bonus);
    	} while (isExistElement(bonus));
    	setBonus(bonus);
    }

    private void initPoison() {
        Poison poison = new Poison();
        do {
            setRandomPosition(poison);
        } while (isExistElement(poison));
        setPoison(poison);
    }

    private void initWall() {
        for (int i = 0; i < Configure.Wall.num; i++) {
            Wall wall = new Wall();
            do {
                setRandomPosition(wall);
            } while (isExistElement(wall));
            ArrayList<Wall> wallList = getWallList();
            if (wallList == null) {
                wallList = new ArrayList<>();
            }
            wallList.add(wall);
            setWallList(wallList);
        }
    }

    public void initSnake() {
        SnakeBody tempSnakeBody = new SnakeBody(Configure.SnakeBody.color);
        do {
            setRandomPosition(tempSnakeBody);
        } while (isExistElement(tempSnakeBody));
        this.snake = new Snake(tempSnakeBody.getX(), tempSnakeBody.getY(), Configure.SnakeBody.color);
    }

    public boolean isExistElement(Element element) {
        ArrayList<Element> blockElements = getBlockElement();
        for (Element e : blockElements) {
            if (e.getX() == element.getX() && e.getY() == element.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage img = ImageIO.read(new File("MySnake/src/resources/bg.jpg"));
            g.drawImage(img, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        renderElement(g);
        renderScore(g);
        if (!snake.isAlive())
            dead(g);
    }

    public void renderScore(Graphics g) {
        g.setColor(snake.getBodyColor());
        g.drawString("Score: " + snake.getScore(), Configure.Element.ElementWidth * (Configure.Board.XNum + 10),
                Configure.Element.ElementWidth * 2);
    }

    public void dead(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Dead!", Configure.Element.ElementWidth * (Configure.Board.XNum + 15),
                Configure.Element.ElementWidth * 2);
    }

    public void run() {
        JPanel thisjpanel = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                thisjpanel.addKeyListener(new Player1KeyListener(snake));
                thisjpanel.setFocusable(true);
                thisjpanel.requestFocusInWindow();
                runSnake(snake);
            }
        }).start();
    }

    public void runSnake(Snake snake) {
        while (snake.isAlive()) {
            ArrayList<Element> elements = getAllElement();
            Element nextElement = null;
            for (Element e : elements) {
                if (e.getX() == snake.nextX() && e.getY() == snake.nextY()){
                    nextElement = e;
                    break;
                }
            }
            snake.eatElement(nextElement);
            handleEatenElement(nextElement);
            repaint();
            try {
                Thread.currentThread().sleep(Configure.Board.INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Element> getAllElement() {
        ArrayList<Element> elements = getBlockElement();
        elements.addAll(snake.getBodies());
        return elements;
    }

    private void renderElement(Graphics g) {
        ArrayList<Element> elements = getAllElement();
        elements.forEach((e) -> {
            fillRect(g, e);
        });
    }

    public void handleEatenElement(Element nextElement) {
        if (nextElement != null && nextElement.isEatable()) {
            if (nextElement instanceof Food ) {
                initFood();
              
            } 
            else if(nextElement instanceof Bonus ) {
            	initBonus();
            }
            else {
                initPoison();
            }
        }
    }

    public ArrayList<Wall> getWallList() {
        return wallList;
    }

    public void setWallList(ArrayList<Wall> wallList) {
        this.wallList = wallList;
    }

    public ArrayList<Wall> getRampart() {
        return rampart;
    }

    public void setRampart(ArrayList<Wall> rampart) {
        this.rampart = rampart;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    
    public Bonus getBonus() {
    	return bonus;
    }
    
    public void setBonus(Bonus bonus) {
    	this.bonus = bonus;
    }

    public Poison getPoison() {
        return poison;
    }

    public void setPoison(Poison poison) {
        this.poison = poison;
    }

    public Snake getSnake() {
        return snake;
    }
}
