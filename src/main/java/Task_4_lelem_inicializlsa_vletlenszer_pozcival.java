import java.util.Random;

class Food {
    private int x;
    private int y;
    private static final Random random = new Random();

    public Food(int maxX, int maxY) {
        this.x = random.nextInt(maxX);
        this.y = random.nextInt(maxY);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
