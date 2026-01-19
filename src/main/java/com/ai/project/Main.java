package com.ai.project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Main class for the Super Mario game, responsible for setting up the game window
 * and serving as the entry point for the application.
 * This class establishes the basic development environment by creating a display frame.
 */
public class Main {

    // Game window dimensions
    public static final int GAME_WIDTH = 800; // Standard width for the game window
    public static final int GAME_HEIGHT = 600; // Standard height for the game window
    public static final String GAME_TITLE = "Super Mario Game"; // Title for the game window

    public static void main(String[] args) {
        // Create the main game window (JFrame)
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        frame.setMinimumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        frame.setMaximumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

        // Set default close operation to exit the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prevent resizing to maintain consistent game dimensions
        frame.setResizable(false);
        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Create a JPanel to draw game content onto. This will be replaced or extended
        // in later tasks to draw game elements. For now, it's a placeholder to show the window.
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK); // A simple background color for the initial setup
        gamePanel.setFocusable(true); // Allow the panel to receive keyboard focus
        gamePanel.requestFocusInWindow(); // Request focus so it can immediately listen for input

        frame.add(gamePanel); // Add the game panel to the frame

        // Make the window visible
        frame.setVisible(true);

        System.out.println("Game development environment and project set up successfully.");
        System.out.println("Game window created with dimensions: " + GAME_WIDTH + "x" + GAME_HEIGHT);
    }
}
