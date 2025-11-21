public class Ball {
    private double x, y; // Current position
    private double vx, vy; // Current velocity
    private double radius;
    private double restitution; // Bounciness factor (0.0 = no bounce, 1.0 = perfect bounce)

    // Environmental constants (can be passed in constructor for more flexibility)
    private static final double GRAVITY = 9.81; // m/s^2 (downwards)
    private static final double FLOOR_Y = 500.0; // Y-coordinate of the floor boundary
    private static final double WALL_LEFT_X = 0.0; // X-coordinate of the left wall boundary
    private static final double WALL_RIGHT_X = 800.0; // X-coordinate of the right wall boundary

    public Ball(double initialX, double initialY, double initialRadius, double initialRestitution) {
        this.x = initialX;
        this.y = initialY;
        this.radius = initialRadius;
        this.restitution = initialRestitution;
        this.vx = 0; // Start with no initial horizontal velocity
        this.vy = 0; // Start with no initial vertical velocity
    }

    /**
     * Updates the ball's position and velocity based on basic physics principles.
     * This method should be called repeatedly in a game loop to simulate continuous motion.
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    public void update(double deltaTime) {
        // 1. Apply gravity to vertical velocity
        vy += GRAVITY * deltaTime;

        // 2. Update position based on current velocity
        x += vx * deltaTime;
        y += vy * deltaTime;

        // 3. Handle collisions with boundaries

        // Floor collision
        if (y + radius > FLOOR_Y) {
            y = FLOOR_Y - radius; // Correct position to be exactly on the floor
            vy *= -restitution;   // Reverse vertical velocity and apply restitution
            // If vertical velocity is very small after bounce, stop it to prevent infinite micro-bounces
            if (Math.abs(vy) < 0.5) { // Threshold for stopping vertical movement
                vy = 0;
            }
            // Optional: Apply some friction to horizontal velocity when on ground
            // vx *= (1.0 - 0.02); // Example: 2% horizontal speed loss per update on ground
        }

        // Left wall collision
        if (x - radius < WALL_LEFT_X) {
            x = WALL_LEFT_X + radius; // Correct position to be against the left wall
            vx *= -restitution;       // Reverse horizontal velocity and apply restitution
            if (Math.abs(vx) < 0.5) { // Threshold for stopping horizontal movement
                vx = 0;
            }
        }

        // Right wall collision
        if (x + radius > WALL_RIGHT_X) {
            x = WALL_RIGHT_X - radius; // Correct position to be against the right wall
            vx *= -restitution;        // Reverse horizontal velocity and apply restitution
            if (Math.abs(vx) < 0.5) { // Threshold for stopping horizontal movement
                vx = 0;
            }
        }
    }

    // --- Getters for accessing ball properties (e.g., for rendering or other interactions) ---
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }

    // --- Setters (e.g., for applying an initial impulse or external forces) ---
    public void setVelocity(double newVx, double newVy) {
        this.vx = newVx;
        this.vy = newVy;
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
}
