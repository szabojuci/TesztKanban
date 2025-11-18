public class Ball {
    private double x;      // X-koordináta
    private double y;      // Y-koordináta
    private double z;      // Z-koordináta
    private double radius; // Sugár
    private String color;  // Szín
    private double mass;   // Tömeg
    private double vx;     // Sebesség X irányban
    private double vy;     // Sebesség Y irányban
    private double vz;     // Sebesség Z irányban

    public Ball(double x, double y, double z, double radius, String color, double mass, double vx, double vy, double vz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.color = color;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    // Getter metódusok
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public double getRadius() { return radius; }
    public String getColor() { return color; }
    public double getMass() { return mass; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getVz() { return vz; }

    // Setter metódusok a dinamikus tulajdonságokhoz (pozíció, sebesség)
    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setVelocity(double vx, double vy, double vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    /**
     * Frissíti a labda pozícióját az aktuális sebessége és az eltelt idő alapján.
     * @param deltaTime Az idő lépés másodpercben.
     */
    public void move(double deltaTime) {
        this.x += this.vx * deltaTime;
        this.y += this.vy * deltaTime;
        this.z += this.vz * deltaTime;
    }

    /**
     * Szimulál egy egyszerű, rugalmatlan ütközést egy adott tengely mentén.
     * Például: a "y" tengely mentén történő ütközés megfordítja a vy sebességkomponenst.
     * @param axis Az ütközés tengelye ("x", "y" vagy "z").
     * @param elasticity A rugalmassági tényező (0.0 = teljesen rugalmatlan, 1.0 = teljesen rugalmas).
     */
    public void bounce(String axis, double elasticity) {
        switch (axis.toLowerCase()) {
            case "x": this.vx *= -elasticity; break;
            case "y": this.vy *= -elasticity; break;
            case "z": this.vz *= -elasticity; break;
        }
    }

    @Override
    public String toString() {
        return String.format("Ball [x=%.2f, y=%.2f, z=%.2f, r=%.2f, color=%s, mass=%.2f, vx=%.2f, vy=%.2f, vz=%.2f]",
                             x, y, z, radius, color, mass, vx, vy, vz);
    }
}
