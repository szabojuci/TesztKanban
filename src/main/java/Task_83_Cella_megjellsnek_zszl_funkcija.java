public class CellFlagger {
    private final boolean[][] flaggedCells;
    private final int rows;
    private final int cols;

    /**
     * Létrehoz egy új CellFlagger objektumot a megadott méretű rácshoz.
     *
     * @param rows A rács sorainak száma.
     * @param cols A rács oszlopainak száma.
     * @throws IllegalArgumentException Ha a sorok vagy oszlopok száma nem pozitív.
     */
    public CellFlagger(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("A rács méreteinek pozitívnak kell lenniük.");
        }
        this.rows = rows;
        this.cols = cols;
        this.flaggedCells = new boolean[rows][cols];
    }

    /**
     * Ellenőrzi, hogy a megadott koordináták érvényesek-e a rácson belül.
     *
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @return true, ha a koordináták érvényesek, egyébként false.
     */
    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Megjelöli (zászlózottá teszi) a megadott cellát.
     *
     * @param row A cella sorindexe.
     * @param col A cella oszlopindexe.
     * @throws IndexOutOfBoundsException Ha a megadott koordináták érvénytelenek.
     */
    public void flagCell(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("Érvénytelen cella koordináták: (" + row + ", " + col + ")");
        }
        flaggedCells[row][col] = true;
    }

    /**
     * Eltávolítja a jelölést (zászlót) a megadott celláról.
     *
     * @param row A cella sorindexe.
     * @param col A cella oszlopindexe.
     * @throws IndexOutOfBoundsException Ha a megadott koordináták érvénytelenek.
     */
    public void unflagCell(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("Érvénytelen cella koordináták: (" + row + ", " + col + ")");
        }
        flaggedCells[row][col] = false;
    }

    /**
     * Váltja a cella zászlózott állapotát (ha zászlózott, eltávolítja, ha nem, zászlózottá teszi).
     *
     * @param row A cella sorindexe.
     * @param col A cella oszlopindexe.
     * @throws IndexOutOfBoundsException Ha a megadott koordináták érvénytelenek.
     */
    public void toggleFlag(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("Érvénytelen cella koordináták: (" + row + ", " + col + ")");
        }
        flaggedCells[row][col] = !flaggedCells[row][col];
    }

    /**
     * Ellenőrzi, hogy a megadott cella zászlózott-e.
     *
     * @param row A cella sorindexe.
     * @param col A cella oszlopindexe.
     * @return true, ha a cella zászlózott, egyébként false.
     * @throws IndexOutOfBoundsException Ha a megadott koordináták érvénytelenek.
     */
    public boolean isFlagged(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException("Érvénytelen cella koordináták: (" + row + ", " + col + ")");
        }
        return flaggedCells[row][col];
    }

    /**
     * Visszaadja a rács sorainak számát.
     * @return A sorok száma.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Visszaadja a rács oszlopainak számát.
     * @return Az oszlopok száma.
     */
    public int getCols() {
        return cols;
    }
}
