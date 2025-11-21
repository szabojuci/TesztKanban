public class GameBoard {

    private char[][] board;
    private int rows;
    private int cols;

    /**
     * Betölti a játéktér adatokat egy String tömbből.
     * Minden String egy sort reprezentál, minden karakter egy cellát.
     * A pálya téglalap alakú lesz, az első sor hossza határozza meg az oszlopok számát.
     * A rövidebb sorok szóközzel egészülnek ki, a hosszabbak levágásra kerülnek.
     *
     * @param mapData A játéktér sorait tartalmazó String tömb.
     */
    public GameBoard(String[] mapData) {
        if (mapData == null || mapData.length == 0) {
            this.board = new char[0][0];
            this.rows = 0;
            this.cols = 0;
            System.err.println("Hiba: A pálya adatok üresek vagy null értékűek.");
            return;
        }

        this.rows = mapData.length;
        this.cols = mapData[0].length(); // Az első sor hossza határozza meg az oszlopok számát

        if (this.cols == 0) {
            this.board = new char[0][0];
            this.rows = 0;
            this.cols = 0;
            System.err.println("Hiba: Az első pálya sor üres.");
            return;
        }

        this.board = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            String rowString = mapData[r];
            for (int c = 0; c < cols; c++) {
                if (c < rowString.length()) {
                    this.board[r][c] = rowString.charAt(c);
                } else {
                    this.board[r][c] = ' '; // Rövidebb sorok kitöltése szóközzel
                }
            }
        }
    }

    /**
     * Megjeleníti a játéktér aktuális állapotát a konzolon.
     */
    public void display() {
        if (board == null || rows == 0 || cols == 0) {
            System.out.println("Üres pálya megjelenítése.");
            return;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println(); // Új sor minden sor után
        }
    }

    /**
     * Visszaadja a játéktér string reprezentációját.
     * @return A játéktér string reprezentációja.
     */
    @Override
    public String toString() {
        if (board == null || rows == 0 || cols == 0) {
            return "Üres Pálya";
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(board[r][c]);
            }
            if (r < rows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Fő metódus a példa futtatásához és a funkcionalitás bemutatásához.
     */
    public static void main(String[] args) {
        // Példa játéktér adatok
        String[] mapLayout = {
            "##########",
            "#........#",
            "#.P......#",
            "#........#",
            "#....#...#",
            "#....####",
            "#....G...#",
            "##########"
        };

        System.out.println("Játéktér betöltése és megjelenítése:");
        GameBoard gameBoard = new GameBoard(mapLayout);
        gameBoard.display();

        System.out.println("\n--- toString() metódus használata ---");
        System.out.println(gameBoard.toString());

        // Példa üres pályával
        System.out.println("\nÜres játéktér betöltése és megjelenítése:");
        GameBoard emptyBoard = new GameBoard(new String[]{});
        emptyBoard.display();

        // Példa inkonzisztens sorhosszúsággal
        String[] inconsistentMap = {
            "#####",
            "#...#",
            "#..P", // Ez a sor rövidebb
            "#####"
        };
        System.out.println("\nInkonzisztens sorhosszúságú játéktér betöltése és megjelenítése:");
        GameBoard inconsistentGameBoard = new GameBoard(inconsistentMap);
        inconsistentGameBoard.display();
    }
}
