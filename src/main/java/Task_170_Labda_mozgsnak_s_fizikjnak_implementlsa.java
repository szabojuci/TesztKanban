public class Ball {
    private double x, y;        // Position (e.g., meters)
    private double vx, vy;      // Velocity (e.g., meters/second)
    private double radius;      // Radius of the ball (e.g., meters)
    private double mass;        // Mass of the ball (e.g., kilograms)
    private double elasticity;  // Coefficient of restitution (0.0 for inelastic, 1.0 for perfectly elastic)
    private static final double GRAVITY = 9.81; // Acceleration due to gravity (m/s^2)

    public Ball(double x, double y, double radius, double initialVx, double initialVy, double mass, double elasticity) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vx = initialVx;
        this.vy = initialVy;
        this.mass = mass;
        // Ensure elasticity is between 0.0 and 1.0
        this.elasticity = Math.max(0.0, Math.min(1.0, elasticity));
    }

    /**
     * Updates the ball's position and velocity based on physics over a given time step.
     * Applies gravity and handles collisions with specified boundaries.
     *
     * @param deltaTime The time elapsed since the last update (in seconds).
     * @param minX      The minimum X-coordinate boundary (left wall).
     * @param maxX      The maximum X-coordinate boundary (right wall).
     * @param minY      The minimum Y-coordinate boundary (top ceiling).
     * @param maxY      The maximum Y-coordinate boundary (bottom floor).
     */
    public void update(double deltaTime, double minX, double maxX, double minY, double maxY) {
        // 1. Apply gravity (acceleration in Y direction)
        vy += GRAVITY * deltaTime;

        // 2. Update position based on current velocity
        x += vx * deltaTime;
        y += vy * deltaTime;

        // 3. Handle boundary collisions
        // Horizontal boundaries
        if (x - radius < minX) {
            x = minX + radius; // Reposition ball to just inside boundary
            vx *= -elasticity; // Reverse velocity and apply elasticity
        } else if (x + radius > maxX) {
            x = maxX - radius; // Reposition ball
            vx *= -elasticity; // Reverse velocity and apply elasticity
        }

        // Vertical boundaries
        if (y - radius < minY) {
            y = minY + radius; // Reposition ball
            vy *= -elasticity; // Reverse velocity and apply elasticity
        } else if (y + radius > maxY) {
            y = maxY - radius; // Reposition ball
            vy *= -elasticity; // Reverse velocity and apply elasticity

            // Optional: If bounce is very small, set velocity to zero to prevent tiny oscillations
            if (Math.abs(vy) < 0.1) { // Threshold for "stopped"
                vy = 0;
            }
        }
    }

    // --- Getters for ball properties ---
    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getRadius() { return radius; }
    public double getMass() { return mass; }
}
