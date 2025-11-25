import java.util.Random;

public class GameBoard {

    public static void generateRandomFood(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return; 
        }

        Random random = new Random();
        int rows = board.length;
        int cols = board[0].length;

        int foodRow = random.nextInt(rows);
        int foodCol = random.nextInt(cols);

        board[foodRow][foodCol] = 1; // Az '1' jelöli az ételt
    }
}
