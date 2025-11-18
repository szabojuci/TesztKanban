import java.util.ArrayList;
import java.util.List;
import java.awt.Point; // Using java.awt.Point for simple coordinate representation

public class GameBoard {
    private final int width;
    private final int height;
    private char[][] board;
    private List<Point> path;
    private Point start;
    private Point end;

    /**
     * Constructs a new GameBoard with specified dimensions.
     * Initializes all cells as empty (' ').
     *
     * @param width The width of the board (number of columns).
     * @param height The height of the board (number of rows).
     */
    public GameBoard(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Board dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
        this.board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' '; // Empty cell
            }
        }
        this.path = new ArrayList<>();
    }

    /**
     * Sets the character type of a specific cell on the board.
     *
     * @param x The x-coordinate (column).
     * @param y The y-coordinate (row).
     * @param type The character representing the cell type (e.g., 'X' for obstacle).
     */
    public void setCell(int x, int y, char type) {
        if (isValidCoordinate(x, y)) {
            board[y][x] = type;
        } else {
            System.err.println("Warning: Attempted to set cell outside board bounds at (" + x + "," + y + ")");
        }
    }

    /**
     * Sets the starting point of the game on the board.
     * Marks the cell with 'S'.
     *
     * @param x The x-coordinate of the start point.
     * @param y The y-coordinate of the start point.
     */
    public void setStart(int x, int y) {
        if (isValidCoordinate(x, y)) {
            this.start = new Point(x, y);
            setCell(x, y, 'S');
        } else {
            System.err.println("Warning: Start point outside board bounds at (" + x + "," + y + ")");
        }
    }

    /**
     * Sets the ending point of the game on the board.
     * Marks the cell with 'E'.
     *
     * @param x The x-coordinate of the end point.
     * @param y The y-coordinate of the end point.
     */
    public void setEnd(int x, int y) {
        if (isValidCoordinate(x, y)) {
            this.end = new Point(x, y);
            setCell(x, y, 'E');
        } else {
            System.err.println("Warning: End point outside board bounds at (" + x + "," + y + ")");
        }
    }

    /**
     * Adds a list of points representing a path to be drawn on the board.
     *
     * @param path A List of Point objects forming the path.
     */
    public void addPath(List<Point> path) {
        this.path = new ArrayList<>(path); // Create a defensive copy
    }

    /**
     * Draws the current state of the game board to the console.
     * Path points are drawn as '.' unless they are the start ('S') or end ('E') points,
     * or an obstacle ('X').
     */
    public void draw() {
        System.out.println("--- Game Board (" + width + "x" + height + ") ---");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char charToDraw = board[i][j];
                // If the cell is part of the path and not 'S' or 'E' or 'X', draw a path marker
                if (pathContains(j, i) && charToDraw != 'S' && charToDraw != 'E' && charToDraw != 'X') {
                    charToDraw = '.';
                }
                System.out.print(charToDraw + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    /**
     * Checks if the given coordinates are within the board boundaries.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return true if the coordinates are valid, false otherwise.
     */
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Checks if the given coordinates are part of the currently stored path.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return true if the point is in the path, false otherwise.
     */
    private boolean pathContains(int x, int y) {
        for (Point p : path) {
            if (p.x == x && p.y == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Main method for demonstrating the GameBoard class.
     */
    public static void main(String[] args) {
        // Create a 7x5 game board
        GameBoard board = new GameBoard(7, 5);

        // Set some obstacles
        board.setCell(1, 1, 'X');
        board.setCell(1, 2, 'X');
        board.setCell(3, 2, 'X');
        board.setCell(3, 3, 'X');
        board.setCell(5, 1, 'X');

        // Set start and end points
        board.setStart(0, 0);
        board.setEnd(6, 4);

        // Define a sample path
        List<Point> samplePath = new ArrayList<>();
        samplePath.add(new Point(0, 0)); // Start
        samplePath.add(new Point(1, 0));
        samplePath.add(new Point(2, 0));
        samplePath.add(new Point(2, 1));
        samplePath.add(new Point(2, 2));
        samplePath.add(new Point(2, 3));
        samplePath.add(new Point(4, 3));
        samplePath.add(new Point(4, 4));
        samplePath.add(new Point(5, 4));
        samplePath.add(new Point(6, 4)); // End

        // Add the path to the board
        board.addPath(samplePath);

        // Draw the board with the path
        board.draw();

        // Example of a board without a path, but with start, end, and obstacles
        System.out.println("\n--- Another Board Example ---");
        GameBoard board2 = new GameBoard(5, 3);
        board2.setStart(0, 2);
        board2.setEnd(4, 0);
        board2.setCell(1, 0, 'X');
        board2.setCell(2, 1, 'X');
        board2.setCell(3, 2, 'X');
        board2.draw();
    }
}
