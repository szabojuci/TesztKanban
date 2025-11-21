import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JPanel implements Runnable {

    private JFrame frame;
    private Thread gameThread;
    private volatile boolean running = false;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final String TITLE = "My Simple Game";
    private final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME_NANOS = 1_000_000_000 / TARGET_FPS;

    public GameWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true); // Allow JPanel to receive focus for input

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this); // Add the JPanel (this) to the JFrame
        frame.pack(); // Size the frame to fit the preferred size of its contents
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);

        // Add a window listener to stop the game thread when the window closes
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame();
            }
        });
    }

    public synchronized void startGame() {
        if (running) return;
        running = true;
        gameThread = new Thread(this, "GameThread");
        gameThread.start();
    }

    public synchronized void stopGame() {
        if (!running) return;
        running = false;
        try {
            gameThread.join(); // Wait for the game thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Game thread interrupted during shutdown.");
        }
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        int fps = 0;

        while (running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            // Update game logic (pass delta time in milliseconds)
            update(updateLength / 1_000_000.0);

            // Render game graphics
            repaint(); // Calls paintComponent

            // Calculate and print FPS every second
            lastFpsTime += updateLength;
            fps++;
            if (lastFpsTime >= 1_000_000_000) {
                System.out.println("FPS: " + fps);
                lastFpsTime = 0;
                fps = 0;
            }

            // Sleep to maintain target FPS
            try {
                long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME_NANOS) / 1_000_000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Game loop sleep interrupted.");
            }
        }
    }

    // Placeholder for game logic update
    private void update(double deltaTimeMillis) {
        // E.g., move player, update scores, check collisions
        // System.out.println("Updating game state... Delta: " + deltaTimeMillis + "ms");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the background

        // Placeholder for drawing game elements
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Game Running!", WIDTH / 2 - g.getFontMetrics().stringWidth("Game Running!") / 2, HEIGHT / 2);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            GameWindow game = new GameWindow();
            game.startGame();
        });
    }
}
