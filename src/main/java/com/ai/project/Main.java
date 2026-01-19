package com.ai.project;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * GameFrame class represents the main window for the Snake Game.
 * It extends JFrame to provide the basic window functionality.
 */
class GameFrame extends JFrame {

    // --- Game Window Constants ---
    public static final int BOARD_WIDTH = 600;  // Width of the game window in pixels
    public static final int BOARD_HEIGHT = 600; // Height of the game window in pixels
    public static final String GAME_TITLE = "Snake Game"; // Title displayed on the window

    /**
     * Constructs a new GameFrame, setting up its basic properties.
     */
    public GameFrame() {
        // Set the title of the window
        setTitle(GAME_TITLE);

        // Set the size of the window
        setSize(BOARD_WIDTH, BOARD_HEIGHT);

        // Define the default close operation: exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevent the user from resizing the window, maintaining consistent game dimensions
        setResizable(false);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window visible to the user
        setVisible(true);
    }
}

/**
 * Main class serves as the entry point for the Snake Game application.
 * It initializes and displays the game window.
 */
public class Main {

    /**
     * The main method where the application execution begins.
     * It ensures that the GUI creation is handled on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the Event Dispatch Thread (EDT):
        // creating and showing the application's GUI.
        // This is crucial for Swing applications to ensure thread safety
        // and proper rendering of UI components.
        SwingUtilities.invokeLater(() -> {
            new GameFrame(); // Create and display the game window
        });
    }
}
