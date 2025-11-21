import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Játék";

    public GameWindow() {
        // Ablak inicializálása
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Középre igazítás
        setResizable(false); // Átméretezhetőség tiltása

        // Játéktér inicializálása (JPanel-ként)
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.DARK_GRAY); // Példa háttérszín
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Preferált méret beállítása

        // Játéktér hozzáadása az ablakhoz
        add(gamePanel);

        // Ablak láthatóvá tétele
        setVisible(true);
    }

    public static void main(String[] args) {
        // A GUI inicializálása az Event Dispatch Thread-en
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
