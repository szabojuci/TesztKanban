public class GameBoard {
    private int rows;
    private int cols;
    private char[][] board;

    public GameBoard(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];

        // Inicializálja a rácsot alapértelmezett üres karakterrel
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.'; // Például pont az üres mező jelölésére
            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " "); // Karakter és szóköz a jobb olvashatóságért
            }
            System.out.println(); // Új sor minden sor után
        }
    }
}
