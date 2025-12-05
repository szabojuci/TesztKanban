import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private static final int LOGICAL_WIDTH = 20; // Labirintus logikai szélessége (cellák száma)
    private static final int LOGICAL_HEIGHT = 10; // Labirintus logikai magassága (cellák száma)
    private static final int ACTUAL_WIDTH = LOGICAL_WIDTH * 2 + 1; // Rács szélessége (falakkal együtt)
    private static final int ACTUAL_HEIGHT = LOGICAL_HEIGHT * 2 + 1; // Rács magassága (falakkal együtt)
    private static final int NUM_POINTS = 15; // Elhelyezendő pontok száma

    private char[][] grid;
    private Random random = new Random();

    public MazeGenerator() {
        grid = new char[ACTUAL_HEIGHT][ACTUAL_WIDTH];
        for (int r = 0; r < ACTUAL_HEIGHT; r++) {
            for (int c = 0; c < ACTUAL_WIDTH; c++) {
                grid[r][c] = '#'; // Kezdetben minden fal
            }
        }
    }

    public char[][] generateMaze() {
        // Indulás egy véletlenszerű, páratlan koordinátájú cellából
        int startX = 1 + random.nextInt(LOGICAL_WIDTH) * 2;
        int startY = 1 + random.nextInt(LOGICAL_HEIGHT) * 2;
        carvePath(startX, startY);

        placePoints();
        return grid;
    }

    private void carvePath(int x, int y) {
        grid[y][x] = ' '; // Jelenlegi cella út

        int[] dx = {2, 0, -2, 0}; // Lépések x irányban (jobb, le, bal, fel)
        int[] dy = {0, 2, 0, -2}; // Lépések y irányban

        List<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) directions.add(i);
        Collections.shuffle(directions, random); // Irányok véletlenszerű sorrendje

        for (int dir : directions) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // Ha a szomszéd a rácson belül van és fal (nem látogatott)
            if (nx > 0 && nx < ACTUAL_WIDTH - 1 && ny > 0 && ny < ACTUAL_HEIGHT - 1 && grid[ny][nx] == '#') {
                grid[y + dy[dir] / 2][x + dx[dir] / 2] = ' '; // Fal eltávolítása
                carvePath(nx, ny); // Rekurzív hívás a szomszédra
            }
        }
    }

    private void placePoints() {
        int pointsPlaced = 0;
        while (pointsPlaced < NUM_POINTS) {
            int r = random.nextInt(ACTUAL_HEIGHT);
            int c = random.nextInt(ACTUAL_WIDTH);

            // Pont elhelyezése csak úton (' ') és ahol még nincs pont ('P')
            if (grid[r][c] == ' ') {
                grid[r][c] = 'P';
                pointsPlaced++;
            }
        }
    }

    public void printMaze() {
        for (int r = 0; r < ACTUAL_HEIGHT; r++) {
            for (int c = 0; c < ACTUAL_WIDTH; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator();
        generator.generateMaze();
        generator.printMaze();
    }
}
