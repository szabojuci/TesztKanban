import java.lang.Math;

/**
 * A Ball class representing a 2D ball with basic physics simulation,
 * including gravity, air resistance, and ground collisions.
 */
public class Ball {
    // State variables
    private double x, y;          // Position (meters)
    private double vx, vy;        // Velocity (m/s)
    private double radius;        // Radius (meters)
    private double mass;          // Mass (kg)

    // Physics parameters
    private static final double GRAVITY = 9.81; // Acceleration due to gravity (m/s^2)
    private double dragCoefficient;             // Air resistance coefficient (e.g., 0.5 * rho * A * Cd)
    private double restitutionCoefficient;      // Bounciness (0.0 = no bounce, 1.0 = perfect bounce)
    private double frictionCoefficient;         // Horizontal friction on impact (0.0 = no loss, 1.0 = complete stop)

    /**
     * Constructs a new Ball object with specified initial conditions and physics properties.
     * @param x Initial X position (meters)
     * @param y Initial Y position (meters)
     * @param vx Initial X velocity (m/s)
     * @param vy Initial Y velocity (m/s)
     * @param radius Ball radius (meters)
     * @param mass Ball mass (kg)
     * @param restitutionCoefficient How bouncy the ball is (0.0 to 1.0)
     * @param frictionCoefficient How much horizontal velocity is lost on impact (0.0 to 1.0)
     * @param dragCoefficient Combined drag coefficient (e.g., 0.5 * airDensity * crossSectionalArea * dragConstant)
     */
    public Ball(double x, double y, double vx, double vy, double radius, double mass,
                double restitutionCoefficient, double frictionCoefficient, double dragCoefficient) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        this.restitutionCoefficient = restitutionCoefficient;
        this.frictionCoefficient = frictionCoefficient;
        this.dragCoefficient = dragCoefficient;
    }

    /**
     * Updates the ball's position and velocity based on physics for a given time step.
     * Uses Euler integration for simplicity.
     * @param deltaTime The time step in seconds.
     */
    public void update(double deltaTime) {
        // --- Calculate forces ---
        double Fx = 0;
        double Fy = -mass * GRAVITY; // Gravity acts downwards

        // Air Resistance (Drag Force: Fd = -dragCoefficient * v^2, opposite to velocity)
        double speed = Math.sqrt(vx * vx + vy * vy);
        if (speed > 1e-6) { // Avoid division by zero and very small speeds
            double dragForceMagnitude = dragCoefficient * speed * speed;
            Fx -= (vx / speed) * dragForceMagnitude;
            Fy -= (vy / speed) * dragForceMagnitude;
        }

        // --- Calculate acceleration ---
        double ax = Fx / mass;
        double ay = Fy / mass;

        // --- Update velocity (Euler integration) ---
        vx += ax * deltaTime;
        vy += ay * deltaTime;

        // --- Update position (Euler integration) ---
        x += vx * deltaTime;
        y += vy * deltaTime;

        // --- Collision Detection and Response (with ground at y=0) ---
        if (y - radius < 0) {
            // Reposition ball to be on the ground
            y = radius;

            // Reverse and reduce vertical velocity (bounce)
            vy = -vy * restitutionCoefficient;

            // Apply friction to horizontal velocity
            vx *= (1.0 - frictionCoefficient);

            // If vertical velocity is very small after bounce, stop it to prevent micro-bounces
            if (Math.abs(vy) < 0.1 * GRAVITY * deltaTime) { // Small threshold based on gravity
                vy = 0;
            }
            // If horizontal velocity is very small, stop it
            if (Math.abs(vx) < 0.1 * GRAVITY * deltaTime) { // Arbitrary small threshold
                 vx = 0;
            }
        }
    }

    // --- Getters for current state ---
    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getRadius() { return radius; }
    public double getMass() { return mass; }
}
