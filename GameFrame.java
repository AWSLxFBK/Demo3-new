package com.game.main;

import com.game.common.Configure;
import com.game.panel.ButtonPanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Snake Game");
        JPanel panel = new ButtonPanel(this);
        this.setContentPane(panel);
        this.setVisible(true);
        this.setSize(Configure.Element.ElementWidth * (Configure.Board.XNum + 20),
                Configure.Element.ElementWidth * (Configure.Board.YNum + 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
