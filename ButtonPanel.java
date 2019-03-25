package com.game.panel;

import com.game.listener.ButtonListener;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(JFrame jFrame) {
        JButton button1 = new JButton("1 Player");
        JButton button2 = new JButton("2 Players");
        button1.addActionListener(new ButtonListener(jFrame));
        button2.addActionListener(new ButtonListener(jFrame));
        this.add(button1);
        this.add(button2);
    }
}
