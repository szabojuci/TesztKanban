import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Pont reprezentálása rekordként a tömörség kedvéért.
record Point(int x, int y) {}

// Irányok definíciója a mozgás koordinátáival.
enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    final int dx, dy;
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

public class SnakeGame {
    private final List<Point> snakeBody;
    private Point food;
    private Direction currentDirection;
    private int score; // Az elfogyasztott ételek száma, ami a kígyó hosszát növeli
    private final int boardWidth, boardHeight;
    private final Random random;

    public SnakeGame(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.random = new Random();
        this.snakeBody = new ArrayList<>();
        snakeBody.add(new Point(0, 0)); // Kezdő kígyó
        snakeBody.add(new Point(1, 0));
        snakeBody.add(new Point(2, 0));
        this.currentDirection = Direction.RIGHT;
        this.score = 0;
        generateFood(); // Első étel elhelyezése
    }

    private void generateFood() {
        Point newFood;
        do {
            newFood = new Point(random.nextInt(boardWidth), random.nextInt(boardHeight));
        } while (snakeBody.contains(newFood)); // Ne spawnoljon a kígyón
        this.food = newFood;
    }

    public void move() {
        Point head = snakeBody.get(snakeBody.size() - 1);
        Point newHead = new Point(
                (head.x() + currentDirection.dx + boardWidth) % boardWidth,
                (head.y() + currentDirection.dy + boardHeight) % boardHeight
        );

        snakeBody.add(newHead); // Új fej hozzáadása

        if (newHead.equals(food)) { // Étel elfogyasztása
            score++;
            generateFood(); // Új étel generálása
            // A kígyó hossza nő, mivel a farkát nem távolítjuk el
        } else {
            snakeBody.remove(0); // Farok eltávolítása, ha nem evett
        }
    }

    public void changeDirection(Direction newDirection) {
        // Megakadályozza az azonnali 180 fokos fordulatot
        if ((newDirection == Direction.LEFT && currentDirection == Direction.RIGHT) ||
            (newDirection == Direction.RIGHT && currentDirection == Direction.LEFT) ||
            (newDirection == Direction.UP && currentDirection == Direction.DOWN) ||
            (newDirection == Direction.DOWN && currentDirection == Direction.UP)) {
            return;
        }
        this.currentDirection = newDirection;
    }

    // Demo fő metódus
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(10, 10);
        System.out.println("Initial snake: " + game.snakeBody + ", Food: " + game.food + ", Score: " + game.score);

        game.move(); // Mozgás jobbra
        System.out.println("After 1st move: " + game.snakeBody + ", Food: " + game.food + ", Score: " + game.score);

        game.changeDirection(Direction.DOWN); // Irányváltás lefelé
        game.move();
        System.out.println("After 2nd move: " + game.snakeBody + ", Food: " + game.food + ", Score: " + game.score);

        // Szimuláljunk ételfogyasztást (manuálisan helyezzük az ételt a fej elé a teszthez)
        game.snakeBody.add(new Point(game.snakeBody.get(game.snakeBody.size()-1).x() + game.currentDirection.dx,
                                     game.snakeBody.get(game.snakeBody.size()-1).y() + game.currentDirection.dy));
        game.food = game.snakeBody.get(game.snakeBody.size()-1); // Étel a fej pozíciójára
        game.snakeBody.remove(game.snakeBody.size()-1); // Visszaállítjuk az eredeti állapotot a move() előtt

        System.out.println("--- Preparing for food consumption ---");
        System.out.println("Snake head before consumption: " + game.snakeBody.get(game.snakeBody.size()-1) + ", Food: " + game.food);

        game.move(); // Étel elfogyasztása
        System.out.println("After food consumption: " + game.snakeBody + ", Food: " + game.food + ", Score: " + game.score);
        System.out.println("Snake length: " + game.snakeBody.size()); // Látható, hogy a kígyó nőtt
    }
}
