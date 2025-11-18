public class GameBoard<T> {
    private final int rows;
    private final int cols;
    private T[][] board;

    /**
     * Létrehoz egy új játéktáblát a megadott méretekkel.
     *
     * @param rows A tábla sorainak száma.
     * @param cols A tábla oszlopainak száma.
     * @throws IllegalArgumentException Ha a sorok vagy oszlopok száma nem pozitív.
     */
    public GameBoard(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("A sorok és oszlopok számának pozitívnak kell lennie.");
        }
        this.rows = rows;
        this.cols = cols;
        // Generikus tömb létrehozása, típusbiztonsági figyelmeztetés elnyomása.
        @SuppressWarnings("unchecked")
        T[][] tempBoard = (T[][]) new Object[rows][cols];
        this.board = tempBoard;
    }

    /**
     * Visszaadja a tábla sorainak számát.
     * @return A sorok száma.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Visszaadja a tábla oszlopainak számát.
     * @return Az oszlopok száma.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Ellenőrzi, hogy a megadott koordináták érvényesek-e a táblán belül.
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @return Igaz, ha a koordináták érvényesek, különben hamis.
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Lekéri a megadott cella tartalmát.
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @return A cella tartalma.
     * @throws IndexOutOfBoundsException Ha a koordináták kívül esnek a táblán.
     */
    public T get(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("A (" + row + ", " + col + ") koordináták kívül esnek a táblán.");
        }
        return board[row][col];
    }

    /**
     * Beállítja a megadott cella tartalmát.
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @param value A cellába helyezendő érték.
     * @throws IndexOutOfBoundsException Ha a koordináták kívül esnek a táblán.
     */
    public void set(int row, int col, T value) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("A (" + row + ", " + col + ") koordináták kívül esnek a táblán.");
        }
        board[row][col] = value;
    }

    /**
     * Törli a tábla összes cellájának tartalmát (null értékre állítja).
     */
    public void clear() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = null;
            }
        }
    }

    /**
     * Visszaadja a tábla string reprezentációját.
     * A null értékű cellákat '.' jelöli.
     * @return A tábla olvasható string reprezentációja.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(board[r][c] == null ? "." : board[r][c].toString());
                if (c < cols - 1) {
                    sb.append(" "); // Szóköz a cellák között
                }
            }
            sb.append("\n"); // Új sor minden sor után
        }
        return sb.toString();
    }
}
