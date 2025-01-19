package app.view;

import javax.swing.*;
import java.awt.*;

class ButtonPanel extends JPanel {
    private JButton clearButton;
    private JButton quitButton;

    public ButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        add(clearButton);
        add(quitButton);
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
