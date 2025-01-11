import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private static int count = 1;

    private void buildContent(){
        // Define a container for UI widgets.
        // Normally it has a layout strategy, which we skip here
        panel = new JPanel();
        // Create the widgets: a label and a button
        label = new JLabel("I'm a label");
        panel.add(label);
        button = new JButton("I'm a button. Press me");
        // When the user presses the button, the button sends an
        // event to an Observer in a callback.
        // The interface to be implemented by the Observer is ActionListener.
        // The callback method is actionPerformed().
        // The Event object is ActionEvent.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Count: " + count++);
            }
        });
        panel.add(button);
        // Add the container to the main application window
        frame.getContentPane().add(panel);
    }

    private void buildAndDisplayGui(){
        // The main application window
        frame = new JFrame("A minimal Swing app");
        buildContent();
        // Tell the window to exit the application when the user closes the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Tell the window to resize itself
        // to have just the right size for the widgets.
        frame.pack();
        // Tell the window to display itself
        // and start the Swing UI thread to process events.
        frame.setVisible(true);
    }

    public static void main(String... args){
        MinimalSwingApp app = new MinimalSwingApp();
        app.buildAndDisplayGui();
        // We can exit the main() method, but the Swing UI thread,
        // and therefore the application, will continue executing.
        // It is Swing that will call System.exit() to exit the
        // application.
    }
}
