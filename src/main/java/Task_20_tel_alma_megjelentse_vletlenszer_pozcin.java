import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomAppleDisplay extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int APPLE_SIZE = 30; // Az alma átmérője

    public RandomAppleDisplay() {
        setTitle("Alma véletlenszerű pozíción");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ablak középre igazítása

        // Egyedi JPanel létrehozása a rajzoláshoz
        JPanel applePanel = new JPanel() {
            private int appleX;
            private int appleY;
            private final Random random = new Random();

            // Véletlenszerű koordináták inicializálása a panel létrehozásakor
            {
                // Biztosítja, hogy az alma teljesen látható legyen az ablakban
                appleX = random.nextInt(WIDTH - APPLE_SIZE);
                appleY = random.nextInt(HEIGHT - APPLE_SIZE);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Hívja az ősosztály metódusát a háttér törléséhez
                Graphics2D g2d = (Graphics2D) g;

                // Az alma (piros kör) rajzolása
                g2d.setColor(Color.RED);
                g2d.fillOval(appleX, appleY, APPLE_SIZE, APPLE_SIZE);
            }
        };

        add(applePanel); // A panel hozzáadása az ablakhoz
        setVisible(true); // Az ablak láthatóvá tétele
    }

    public static void main(String[] args) {
        // Biztosítja, hogy a GUI frissítések az Event Dispatch Thread-en történjenek
        SwingUtilities.invokeLater(RandomAppleDisplay::new);
    }
}
