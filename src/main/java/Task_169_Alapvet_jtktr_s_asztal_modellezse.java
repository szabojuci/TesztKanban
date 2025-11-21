public class GameBoard {
    private final int rows;
    private final int cols;
    private CellState[][] board;

    public enum CellState {
        EMPTY,
        PLAYER_ONE,
        PLAYER_TWO // Testreszabható: pl. OBSTACLE, ITEM, stb.
    }

    public GameBoard(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("A sorok és oszlopok számának pozitívnak kell lennie.");
        }
        this.rows = rows;
        this.cols = cols;
        this.board = new CellState[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public CellState getCellState(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("A koordináták (" + row + ", " + col + ") kívül esnek a táblán.");
        }
        return board[row][col];
    }

    public void setCellState(int row, int col, CellState state) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("A koordináták (" + row + ", " + col + ") kívül esnek a táblán.");
        }
        this.board[row][col] = state;
    }

    public boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean isCellEmpty(int row, int col) {
        return isValidCoordinate(row, col) && board[row][col] == CellState.EMPTY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (board[i][j]) {
                    case EMPTY:
                        sb.append(" . ");
                        break;
                    case PLAYER_ONE:
                        sb.append(" X ");
                        break;
                    case PLAYER_TWO:
                        sb.append(" O ");
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
