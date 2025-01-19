package app.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;
    private ButtonPanel buttonPanel;

    public MainFrame(String title) {
        super(title);
        buildFrame();
    }

    private void buildFrame() {
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BorderLayout for the main frame
        getContentPane().setLayout(new BorderLayout());

        // Create and add the drawing panel (center)
        drawingPanel = new DrawingPanel();
        getContentPane().add(drawingPanel, BorderLayout.CENTER);

        // Create and add the button panel (bottom)
        buttonPanel = new ButtonPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Configure button actions
        buttonPanel.getClearButton().addActionListener(e -> drawingPanel.clearDisks());
        buttonPanel.getQuitButton().addActionListener(e -> System.exit(0));

        // Size and display
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null); // Center on screen
    }
}
