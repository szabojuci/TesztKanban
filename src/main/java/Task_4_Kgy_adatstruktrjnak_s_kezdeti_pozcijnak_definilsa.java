import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> body; // A kígyó testét alkotó szegmensek listája

    /**
     * Létrehoz egy új kígyót a megadott kezdeti pozícióval és hosszal.
     * A kígyó vízszintesen inicializálódik, a fej a (headX, headY) ponton,
     * a többi szegmens tőle balra.
     *
     * @param headX         A kígyó fejének kezdeti X koordinátája.
     * @param headY         A kígyó fejének kezdeti Y koordinátája.
     * @param initialLength A kígyó kezdeti hossza (szegmensek száma).
     */
    public Snake(int headX, int headY, int initialLength) {
        body = new ArrayList<>();
        for (int i = 0; i < initialLength; i++) {
            body.add(new Point(headX - i, headY));
        }
    }

    /**
     * Visszaadja a kígyó testét alkotó pontok listáját.
     *
     * @return A kígyó testét reprezentáló Point objektumok listája.
     */
    public List<Point> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Snake [body=" + body + "]";
    }
}
