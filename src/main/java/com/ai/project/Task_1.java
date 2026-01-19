package com.ai.project;

/**
 * Defines the core game mechanics constants for the Super Mario game.
 * This class serves as a central repository for numerical values that govern
 * player actions (run, jump, stomp) and environmental physics (gravity).
 * These constants will be used by other components to implement the actual
 * movement and interaction logic.
 */
public final class GameMechanics {

    // Player Movement Constants
    /**
     * The horizontal speed at which the player character moves.
     * This value represents the maximum horizontal velocity in pixels per second.
     */
    public static final float PLAYER_RUN_SPEED = 150.0f;

    /**
     * The initial vertical velocity applied to the player character when a jump is initiated.
     * A negative value indicates upward movement, assuming a Y-down coordinate system
     * where Y increases downwards. Units are pixels per second.
     */
    public static final float PLAYER_JUMP_INITIAL_VELOCITY = -350.0f;

    // Environmental Physics Constants
    /**
     * The acceleration due to gravity, applied vertically to all entities affected by physics.
     * A positive value indicates downward acceleration, consistent with a Y-down coordinate system.
     * Units are pixels per second squared.
     */
    public static final float GRAVITY_ACCELERATION = 800.0f;

    // Stomp Mechanic Constants
    /**
     * The vertical velocity applied to the player character immediately after successfully
     * stomping an enemy. This creates a small upward bounce, which is a key part of the
     * stomp mechanic's feel and gameplay loop. A negative value indicates upward movement.
     * Units are pixels per second.
     */
    public static final float PLAYER_STOMP_BOUNCE_VELOCITY = -200.0f;

    /**
     * Private constructor to prevent instantiation of this utility class.
     * All members are static and final, meant to be accessed directly.
     */
    private GameMechanics() {
        // This class is not meant to be instantiated.
    }
}
