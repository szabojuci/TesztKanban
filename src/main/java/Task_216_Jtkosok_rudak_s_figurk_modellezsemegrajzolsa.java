import java.awt.Color;
import java.awt.Graphics;

/**
 * Enum to differentiate between player types: rods and figures.
 */
enum PlayerType {
    ROD,    // Represents a rod-like player, typically rectangular.
    FIGURE  // Represents a figure-like player, typically circular or oval.
}

/**
 * A Player class to model and draw game entities like rods and figures.
 * Each player has an ID, name, type, position, dimensions, and color.
 */
class Player {
    private String id;
    private String name;
    private PlayerType type;
    private int x, y;          // Top-left corner for drawing (or bounding box top-left for figures).
    private int width, height; // Dimensions: width and height for rods; bounding box dimensions for figures.
    private Color color;       // The color to draw the player.

    /**
     * Constructs a new Player object.
     *
     * @param id     A unique identifier for the player.
     * @param name   A descriptive name for the player.
     * @param type   The type of the player (ROD or FIGURE).
     * @param x      The x-coordinate of the player's position.
     * @param y      The y-coordinate of the player's position.
     * @param width  The width of the player (or bounding box width for figures).
     * @param height The height of the player (or bounding box height for figures).
     * @param color  The color of the player.
     */
    public Player(String id, String name, PlayerType type, int x, int y, int width, int height, Color color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // --- Getters and Setters (essential for accessing and modifying player state) ---

    public String getId() { return id; }
    public String getName() { return name; }
    public PlayerType getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public void setColor(Color color) { this.color = color; }
    public void setDimensions(int width, int height) { this.width = width; this.height = height; }

    /**
     * Draws the player on the given Graphics context.
     * The drawing style depends on the player's type:
     * - RODs are drawn as filled rectangles.
     * - FIGUREs are drawn as filled ovals.
     *
     * @param g The Graphics object to draw on.
     */
    public void draw(Graphics g) {
        g.setColor(this.color); // Set the drawing color to the player's color

        switch (this.type) {
            case ROD:
                // Draw a filled rectangle for a rod
                g.fillRect(x, y, width, height);
                break;
            case FIGURE:
                // Draw a filled oval for a figure. Assumes width/height define its bounding box.
                g.fillOval(x, y, width, height);
                break;
            default:
                // Fallback for unknown types (should not occur with the current enum definition)
                System.err.println("Warning: Attempted to draw player of unknown type: " + this.type);
                break;
        }
    }
}
