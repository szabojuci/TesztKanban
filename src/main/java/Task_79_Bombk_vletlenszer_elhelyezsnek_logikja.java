import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BombPlacementLogic {

    /**
     * Generál egy rácsot, amelyen a bombák véletlenszerűen vannak elhelyezve.
     *
     * @param rows A rács sorainak száma.
     * @param cols A rács oszlopainak száma.
     * @param numBombs Az elhelyezendő bombák száma.
     * @return Egy kétdimenziós boolean tömb, ahol 'true' jelenti a bombát, 'false' pedig üres mezőt.
     */
    public static boolean[][] placeBombs(int rows, int cols, int numBombs) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("A sorok és oszlopok számának pozitívnak kell lennie.");
        }

        boolean[][] grid = new boolean[rows][cols];
        int totalCells = rows * cols;

        // Érvénytelen bombaszám kezelése: 0 és a teljes cellaszám között legyen.
        numBombs = Math.max(0, Math.min(numBombs, totalCells));

        // Létrehozzuk az összes lehetséges cella 1D indexét.
        List<Integer> cellIndices = new ArrayList<>(totalCells);
        for (int i = 0; i < totalCells; i++) {
            cellIndices.add(i);
        }

        // Véletlenszerűen megkeverjük az indexeket.
        Collections.shuffle(cellIndices, new Random());

        // Kivesszük az első 'numBombs' indexet, és bombákat helyezünk el ezeken a pozíciókon.
        for (int i = 0; i < numBombs; i++) {
            int index = cellIndices.get(i);
            int r = index / cols; // Sor index
            int c = index % cols; // Oszlop index
            grid[r][c] = true;
        }

        return grid;
    }
}
