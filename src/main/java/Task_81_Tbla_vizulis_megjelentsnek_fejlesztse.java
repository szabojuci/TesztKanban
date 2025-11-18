import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Fő osztály a tábla vizuális megjelenítésének demonstrálására.
 * Létrehoz egy JFrame-et, amelyben egy BoardPanel található.
 * Lehetővé teszi a cellák állapotának interaktív változtatását kattintással.
 */
public class BoardVisualizer extends JFrame {

    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int CELL_SIZE = 60; // Egy cella mérete pixelekben

    private BoardPanel boardPanel;

    public BoardVisualizer() {
        setTitle("Tábla Vizuális Megjelenítés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // A tábla fix méretű

        boardPanel = new BoardPanel(ROWS, COLS, CELL_SIZE);
        add(boardPanel, BorderLayout.CENTER);

        // Példa: Néhány kezdeti állapot beállítása a táblán
        boardPanel.setCellState(0, 0, 1); // Játékos 1
        boardPanel.setCellState(1, 1, 2); // Játékos 2
        boardPanel.setCellState(2, 3, 1);
        boardPanel.setCellState(7, 7, 2);

        // Egyszerű interakció hozzáadása: kattintásra változik a cella állapota
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / CELL_SIZE;
                int row = e.getY() / CELL_SIZE;

                if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                    int currentState = boardPanel.getCellState(row, col);
                    // Állapotok ciklikus váltása: 0 (üres) -> 1 (játékos 1) -> 2 (játékos 2) -> 0
                    int newState = (currentState + 1) % 3;
                    boardPanel.setCellState(row, col, newState);
                }
            }
        });

        pack(); // Az ablak méretének beállítása a panelhez
        setLocationRelativeTo(null); // Ablak középre helyezése
        setVisible(true);
    }

    public static void main(String[] args) {
        // A GUI frissítéseket az Event Dispatch Thread-en kell futtatni
        SwingUtilities.invokeLater(BoardVisualizer::new);
    }
}

/**
 * A tábla vizuális megjelenítéséért felelős JPanel.
 * Rajzolja a rácsot és a cellákat az állapotuknak megfelelő színnel.
 */
class BoardPanel extends JPanel {

    private final int rows;
    private final int cols;
    private final int cellSize;
    private final int[][] boardState; // 0: üres, 1: játékos 1, 2: játékos 2

    // Színek az egyes állapotokhoz
    private static final Color EMPTY_COLOR = Color.LIGHT_GRAY;
    private static final Color PLAYER1_COLOR = Color.BLUE;
    private static final Color PLAYER2_COLOR = Color.RED;
    private static final Color GRID_COLOR = Color.DARK_GRAY;

    public BoardPanel(int rows, int cols, int cellSize) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.boardState = new int[rows][cols];

        // A tábla inicializálása üres állapotra
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardState[i][j] = 0;
            }
        }
    }

    /**
     * Beállítja egy adott cella állapotát és kéri a panel újrarajzolását.
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @param state Az új állapot (0: üres, 1: játékos 1, 2: játékos 2).
     */
    public void setCellState(int row, int col, int state) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            boardState[row][col] = state;
            repaint(); // Kéri a vizuális frissítést
        }
    }

    /**
     * Lekéri egy adott cella aktuális állapotát.
     * @param row A sor indexe.
     * @param col Az oszlop indexe.
     * @return A cella állapota, vagy -1, ha az indexek érvénytelenek.
     */
    public int getCellState(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return boardState[row][col];
        }
        return -1;
    }

    /**
     * Felülírja a paintComponent metódust a tábla egyedi rajzolásához.
     * Először a cellákat rajzolja ki, majd a rácsvonalakat a tetejére.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Hívja a szülő osztály metódusát a háttér rajzolásához
        Graphics2D g2d = (Graphics2D) g;

        // Cellák rajzolása
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = c * cellSize;
                int y = r * cellSize;

                Color cellColor;
                switch (boardState[r][c]) {
                    case 1:
                        cellColor = PLAYER1_COLOR;
                        break;
                    case 2:
                        cellColor = PLAYER2_COLOR;
                        break;
                    default: // 0 vagy bármely más érték (üres)
                        cellColor = EMPTY_COLOR;
                        break;
                }

                g2d.setColor(cellColor);
                g2d.fillRect(x, y, cellSize, cellSize); // Kitöltött téglalap a cellának
            }
        }

        // Rácsvonalak rajzolása a cellák tetejére
        g2d.setColor(GRID_COLOR);
        for (int r = 0; r <= rows; r++) {
            g2d.drawLine(0, r * cellSize, cols * cellSize, r * cellSize); // Vízszintes vonalak
        }
        for (int c = 0; c <= cols; c++) {
            g2d.drawLine(c * cellSize, 0, c * cellSize, rows * cellSize); // Függőleges vonalak
        }
    }

    /**
     * Visszaadja a panel preferált méretét, ami a tábla teljes mérete.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cols * cellSize, rows * cellSize);
    }
}
