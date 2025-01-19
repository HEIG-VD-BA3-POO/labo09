import javax.swing.SwingUtilities;

import app.view.MainFrame;

public class Main {
    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame("Disk Drawing Application");
            frame.setVisible(true);
        });
    }
}
