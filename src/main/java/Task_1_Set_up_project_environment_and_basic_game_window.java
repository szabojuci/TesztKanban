import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class GameWindow extends JPanel {

    public GameWindow() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Game rendering logic will go here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game Window");
        GameWindow gamePanel = new GameWindow();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
