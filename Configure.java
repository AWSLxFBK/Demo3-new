package com.game.common;

import java.awt.*;

public class Configure {
    public static class Food {
        public static Color color = Color.GREEN;
        public static final int score = 1;
    }
    
    public static class Bonus {
    	public static Color color = Color.BLUE;
    	public static final int score = 2;
    }

    public static class SnakeBody {
        public static Color color = Color.ORANGE;
        public static Color color2 = Color.CYAN;
    }

    public static class Poison {
        public static Color color = Color.RED;
        public static final int score = -1;
    }

    public static class Wall {
        public static Color color = Color.BLACK;
        public static final int num = 3;
    }

    public static class Element {
        public static final int ElementWidth = 20;
    }

    public static class Board {
        public static final int XNum = 40;
        public static final int YNum = 20;
        public static final int INTERVAL = 300;
    }
}
