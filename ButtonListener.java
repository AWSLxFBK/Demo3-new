package com.game.listener;

import com.game.panel.PlayerPanel;
import com.game.panel.TwoPlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private JFrame jFrame;
    public ButtonListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeButton(e);
        if (e.getActionCommand().equals("1 Player"))
            changePanel(new PlayerPanel());
        else
            changePanel(new TwoPlayerPanel());
    }

    private void removeButton(ActionEvent e) {
        JButton clickButton = (JButton)e.getSource();
        Container parent = clickButton.getParent();
        parent.removeAll();
        parent.repaint();
    }

    private void changePanel(JPanel jPanel) {
        jFrame.setContentPane(jPanel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
