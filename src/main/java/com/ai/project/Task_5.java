package com.ai.project;

/**
 * Enum representing different types of tiles that can exist on the game map.
 * This is a foundational component for map design and collision detection.
 */
enum TileType {
    WALL,   // Impassable obstacle
    PATH,   // Walkable empty space
    DOT,    // Walkable space containing a dot (consumable)
    POWER_PELLET, // Walkable space containing a power pellet (consumable)
    GHOST_LAIR_DOOR, // Special tile, usually passable for ghosts but not Pacman initially
    EMPTY_GHOST_LAIR // Walkable space inside the ghost lair
}
