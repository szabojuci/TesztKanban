import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PlayerMovementApp extends JFrame {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private final GamePanel gamePanel;

    public PlayerMovementApp() {
        setTitle("Játékos Mozgatás");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Középre igazítás

        gamePanel = new GamePanel();
        add(gamePanel);

        // A panelnek fókuszba kell kerülnie a billentyűzetes eseményekhez,
        // bár az InputMap/ActionMap WHEN_IN_FOCUSED_WINDOW módban kevésbé igényli.
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        setVisible(true);
    }

    private class GamePanel extends JPanel {
        private int playerX = 50;
        private int playerY = 50;
        private final int playerWidth = 20;
        private final int playerHeight = 80; // "Rúd" alak
        private final int moveStep = 10;

        public GamePanel() {
            setBackground(Color.BLACK);
            setupKeyBindings();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(playerX, playerY, playerWidth, playerHeight);
        }

        private void setupKeyBindings() {
            // Billentyűkötések beállítása az InputMap és ActionMap segítségével.
            // WHEN_IN_FOCUSED_WINDOW biztosítja, hogy a billentyűk akkor is működjenek,
            // ha a panel nincs közvetlenül fókuszban, de az ablak igen.
            InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = getActionMap();

            // Fel mozgás
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
            actionMap.put("moveUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerY = Math.max(0, playerY - moveStep);
                    repaint(); // Újrarajzolás
                }
            });

            // Le mozgás
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
            actionMap.put("moveDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerY = Math.min(getHeight() - playerHeight, playerY + moveStep);
                    repaint();
                }
            });

            // Balra mozgás
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
            actionMap.put("moveLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerX = Math.max(0, playerX - moveStep);
                    repaint();
                }
            });

            // Jobbra mozgás
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
            actionMap.put("moveRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerX = Math.min(getWidth() - playerWidth, playerX + moveStep);
                    repaint();
                }
            });
        }
    }

    public static void main(String[] args) {
        // A GUI frissítéseket az Event Dispatch Thread-en kell futtatni
        SwingUtilities.invokeLater(PlayerMovementApp::new);
    }
}
