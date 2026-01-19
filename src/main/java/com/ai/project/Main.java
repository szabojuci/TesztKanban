package com.ai.project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Main class to launch the Super Mario game application.
 * This sets up the JFrame and initializes the GameEngine.
 */
public class Main {
    public static void main(String[] args) {
        // Create the core game engine panel
        GameEngine gameEngine = new GameEngine();

        // Set up the main application window (JFrame)
        JFrame frame = new JFrame(GameEngine.TITLE);
        frame.setPreferredSize(new Dimension(GameEngine.WIDTH, GameEngine.HEIGHT));
        frame.setMaximumSize(new Dimension(GameEngine.WIDTH, GameEngine.HEIGHT));
        frame.setMinimumSize(new Dimension(GameEngine.WIDTH, GameEngine.HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.setResizable(false); // Prevent resizing
        frame.add(gameEngine); // Add the GameEngine panel to the frame
        frame.pack(); // Pack components to their preferred sizes
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true); // Make the window visible

        // Start the game loop in a separate thread
        gameEngine.start();
    }
}

/**
 * GameEngine class serves as the core of the game, handling the game loop,
 * updates, and rendering. It extends JPanel to allow custom drawing.
 */
class GameEngine extends JPanel implements Runnable {

    // Game window dimensions and title
    public static final int WIDTH = 800; // Width of the game window
    public static final int HEIGHT = 600; // Height of the game window
    public static final String TITLE = "Super Mario Game"; // Title of the game window

    // Game loop timing constants
    public static final int TARGET_FPS = 60; // Desired frames per second
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // Time in nanoseconds for one frame

    private Thread gameThread; // The thread running the game loop
    private volatile boolean running = false; // Flag to control the game loop

    /**
     * Constructor for GameEngine.
     * Sets up panel properties like size, focusability, and background color.
     */
    public GameEngine() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true); // Allow the panel to receive keyboard input
        requestFocusInWindow(); // Request focus immediately for input
        setBackground(Color.BLACK); // Default background color
    }

    /**
     * Starts the game loop in a new thread.
     * Ensures the game doesn't start if it's already running.
     */
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this, "GameThread");
        gameThread.start();
    }

    /**
     * Stops the game loop and waits for the game thread to terminate.
     * Ensures the game doesn't stop if it's not running.
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            gameThread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Game thread interrupted while stopping.");
        }
    }

    /**
     * The main game loop.
     * This method is executed by the gameThread.
     */
    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        // long lastFpsTime = 0; // Optional: for debugging FPS
        // int fps = 0; // Optional: for debugging FPS

        while (running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            // Calculate delta for frame-rate independent updates
            // delta = 1.0 means frame took exactly OPTIMAL_TIME
            // delta > 1.0 means frame took longer (game is lagging)
            // delta < 1.0 means frame was faster
            double delta = updateLength / (double) OPTIMAL_TIME;

            // --- Game Logic Update ---
            update(delta);

            // --- Game Rendering ---
            render();

            // --- Optional: FPS Counter Update ---
            /*
            lastFpsTime += updateLength;
            fps++;
            if (lastFpsTime >= 1000000000) { // Every second
                System.out.println("FPS: " + fps);
                lastFpsTime = 0;
                fps = 0;
            }
            */

            // --- Sleep to maintain target FPS ---
            try {
                // Calculate time to sleep to reach OPTIMAL_TIME
                long gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (gameTime > 0) {
                    Thread.sleep(gameTime);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                System.err.println("Game thread interrupted during sleep.");
                stop(); // Stop the game if the thread is interrupted
            }
        }
    }

    /**
     * Placeholder for all game state updates.
     * This method will be responsible for updating player positions, enemy AI,
     * collision detection, score, etc.
     *
     * @param delta The time multiplier for frame-rate independent updates.
     */
    private void update(double delta) {
        // Example: If a player moves at 'speed' pixels per second,
        // player.x += player.speed * delta;
        // For now, this is empty.
    }

    /**
     * Triggers the repaint method to render game elements on the screen.
     */
    private void render() {
        repaint(); // Calls paintComponent(Graphics g)
    }

    /**
     * Custom painting method for the JPanel.
     * All game drawing operations should be performed here.
     *
     * @param g The Graphics context used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen and paints background

        // --- Placeholder Rendering ---
        // For now, draw a simple red rectangle to confirm rendering is working.
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100);

        // In future tasks, this will contain calls to draw game objects:
        // player.draw(g);
        // level.draw(g);
        // enemies.forEach(enemy -> enemy.draw(g));
        // ui.draw(g);

        // Ensure all drawing operations are completed before showing on screen
        java.awt.Toolkit.getDefaultToolkit().sync();
    }
}
