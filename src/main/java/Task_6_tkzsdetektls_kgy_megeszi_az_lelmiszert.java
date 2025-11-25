import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Reprezentál egy 2D koordinátát.
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

// Reprezentálja a kígyót, a fejét az első elem a listában.
class Snake {
    private List<Point> body;

    Snake(Point headPosition) {
        this.body = new ArrayList<>();
        this.body.add(headPosition); // A kígyó fejjel indul
    }

    public Point getHead() {
        return body.get(0);
    }
}

// Reprezentálja az élelmiszert.
class Food {
    private Point position;

    Food(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}

// Ütközésdetektálási logikát tartalmazó osztály.
public class CollisionDetector {

    /**
     * Ellenőrzi, hogy a kígyó feje ütközött-e az élelmiszerrel.
     *
     * @param snake A kígyó objektum.
     * @param food Az élelmiszer objektum.
     * @return true, ha ütközés történt, egyébként false.
     */
    public static boolean checkSnakeFoodCollision(Snake snake, Food food) {
        return snake.getHead().equals(food.getPosition());
    }
}
