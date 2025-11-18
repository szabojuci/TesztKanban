class BombSweeper {

    /**
     * Kiszámítja és tárolja a szomszédos bombák számát egy bombamező minden cellájára.
     *
     * @param bombGrid Egy 2D-s boolean tömb, ahol 'true' jelöl egy bombát, 'false' pedig egy üres mezőt.
     * @return Egy 2D-s int tömb, amely minden cellához tartalmazza a szomszédos (8 irányú) bombák számát.
     */
    public static int[][] calculateNeighborBombs(boolean[][] bombGrid) {
        if (bombGrid == null || bombGrid.length == 0 || bombGrid[0].length == 0) {
            return new int[0][0]; // Üres vagy érvénytelen rács kezelése
        }

        int rows = bombGrid.length;
        int cols = bombGrid[0].length;
        int[][] neighborBombCounts = new int[rows][cols];

        // 8 irány a szomszédok ellenőrzéséhez: fel, le, bal, jobb, 4 átlós irány
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}; // sor eltolások
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1}; // oszlop eltolások

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i]; // szomszéd sor indexe
                    int nc = c + dc[i]; // szomszéd oszlop indexe

                    // Ellenőrizzük, hogy a szomszéd a rácson belül van-e
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        if (bombGrid[nr][nc]) {
                            count++;
                        }
                    }
                }
                neighborBombCounts[r][c] = count;
            }
        }

        return neighborBombCounts;
    }
}
