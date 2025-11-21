public class PacMan {
    private int x;
    private int y;
    private Direction currentDirection;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Létrehoz egy Pac-Man karaktert a megadott kezdőpozícióval és iránnyal.
     * @param startX A kezdő X koordináta.
     * @param startY A kezdő Y koordináta.
     * @param initialDirection A kezdeti irány.
     */
    public PacMan(int startX, int startY, Direction initialDirection) {
        this.x = startX;
        this.y = startY;
        this.currentDirection = initialDirection;
    }

    /**
     * Elmozdítja a Pac-Man-t az aktuális iránya szerint egy lépéssel.
     * (A valós játékban itt történne ütközésvizsgálat is a pályával.)
     */
    public void move() {
        switch (currentDirection) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }

    /**
     * Megváltoztatja a Pac-Man mozgásirányát.
     * @param newDirection Az új irány.
     */
    public void changeDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    // --- Getter metódusok az aktuális állapot lekérdezéséhez ---

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public String toString() {
        return "PacMan [x=" + x + ", y=" + y + ", direction=" + currentDirection + "]";
    }
}
