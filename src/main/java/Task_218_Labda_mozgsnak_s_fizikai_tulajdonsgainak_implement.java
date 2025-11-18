public class Ball {

    // Inner class for 3D vector operations to keep it self-contained
    private static class Vector3D {
        public double x, y, z;

        public Vector3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        // Add another vector to this vector
        public void add(Vector3D other) {
            this.x += other.x;
            this.y += other.y;
            this.z += other.z;
        }

        // Scale this vector by a factor
        public void scale(double factor) {
            this.x *= factor;
            this.y *= factor;
            this.z *= factor;
        }
    }

    private Vector3D position;
    private Vector3D velocity;
    private double radius;
    private double mass; // Mass is included for completeness, but not directly used in this simplified model for gravity/collision
    
    // Physical constants
    private static final double GRAVITY = 9.81; // Acceleration due to gravity (m/s^2)
    private static final double RESTITUTION_COEFFICIENT = 0.7; // Bounciness factor (0.0 = no bounce, 1.0 = perfect bounce)
    private static final double FRICTION_FACTOR = 0.05; // Simple friction applied on ground contact
    private static final double MIN_VELOCITY_THRESHOLD = 0.1; // Velocity below which the ball is considered stopped

    public Ball(double startX, double startY, double startZ, double startVx, double startVy, double startVz, double radius, double mass) {
        this.position = new Vector3D(startX, startY, startZ);
        this.velocity = new Vector3D(startVx, startVy, startVz);
        this.radius = radius;
        this.mass = mass;
    }

    // Getters for external access (e.g., rendering engine)
    public Vector3D getPosition() {
        return position;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Updates the ball's physical state (position and velocity) for a given time step.
     * This method applies gravity and handles simple ground collisions.
     *
     * @param deltaTime The duration of the time step in seconds.
     */
    public void update(double deltaTime) {
        // 1. Apply gravity to vertical velocity
        velocity.y -= GRAVITY * deltaTime;

        // 2. Update position based on current velocity
        position.add(new Vector3D(velocity.x * deltaTime, velocity.y * deltaTime, velocity.z * deltaTime));

        // 3. Handle ground collision (assuming ground is at y=0)
        if (position.y - radius < 0) {
            // Correct position to be exactly on the ground
            position.y = radius;

            // Reverse vertical velocity and apply restitution (bounce)
            velocity.y *= -RESTITUTION_COEFFICIENT;

            // Apply simple friction to horizontal components when touching the ground
            // This is a basic model, not physically accurate rolling/sliding friction
            velocity.x *= (1.0 - FRICTION_FACTOR);
            velocity.z *= (1.0 - FRICTION_FACTOR);

            // If the ball's velocity is very low, stop it to prevent tiny, endless bounces/slides
            if (Math.abs(velocity.y) < MIN_VELOCITY_THRESHOLD &&
                Math.abs(velocity.x) < MIN_VELOCITY_THRESHOLD &&
                Math.abs(velocity.z) < MIN_VELOCITY_THRESHOLD) {
                velocity.x = 0;
                velocity.y = 0;
                velocity.z = 0;
            }
        }
    }
}
