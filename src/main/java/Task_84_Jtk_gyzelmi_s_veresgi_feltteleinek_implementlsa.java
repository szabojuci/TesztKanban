import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameConditions {

    /**
     * Represents the possible outcomes or states of the game.
     */
    public enum GameOutcome {
        PLAYER_1_WINS,
        PLAYER_2_WINS,
        DRAW,
        ONGOING,
        INVALID_STATE // Represents an error or unplayable state
    }

    /**
     * A simple representation of the game's current state.
     * In a real game, this would be much more complex (e.g., board, piece positions, etc.).
     */
    public static class GameState {
        private final Map<String, Integer> playerScores;
        private final int currentTurn;
        private final int maxTurns;
        private final int winningScoreThreshold;
        private final String currentPlayerId; // E.g., "Player1", "Player2"

        public GameState(int initialPlayer1Score, int initialPlayer2Score, int currentTurn, int maxTurns, int winningScoreThreshold, String currentPlayerId) {
            this.playerScores = new HashMap<>();
            this.playerScores.put("Player1", initialPlayer1Score);
            this.playerScores.put("Player2", initialPlayer2Score);
            this.currentTurn = currentTurn;
            this.maxTurns = maxTurns;
            this.winningScoreThreshold = winningScoreThreshold;
            this.currentPlayerId = currentPlayerId;
        }

        public int getScore(String playerId) {
            return playerScores.getOrDefault(playerId, 0);
        }

        public int getCurrentTurn() {
            return currentTurn;
        }

        public int getMaxTurns() {
            return maxTurns;
        }

        public int getWinningScoreThreshold() {
            return winningScoreThreshold;
        }

        public String getCurrentPlayerId() {
            return currentPlayerId;
        }
    }

    /**
     * Evaluates the current game state to determine the outcome.
     * This method encapsulates the core win, loss, and draw conditions.
     *
     * @param state The current GameState object.
     * @return The GameOutcome representing the result for the current player.
     */
    public static GameOutcome checkGameOutcome(GameState state) {
        Objects.requireNonNull(state, "Game state cannot be null.");

        // Retrieve scores and thresholds
        int player1Score = state.getScore("Player1");
        int player2Score = state.getScore("Player2");
        int winningScore = state.getWinningScoreThreshold();
        int currentTurn = state.getCurrentTurn();
        int maxTurns = state.getMaxTurns();

        // 1. Check for immediate win conditions based on score
        if (player1Score >= winningScore && player1Score > player2Score) {
            return GameOutcome.PLAYER_1_WINS;
        }
        if (player2Score >= winningScore && player2Score > player1Score) {
            return GameOutcome.PLAYER_2_WINS;
        }
        // Handle cases where both reach winning score simultaneously (e.g., real-time game)
        if (player1Score >= winningScore && player2Score >= winningScore) {
            if (player1Score > player2Score) return GameOutcome.PLAYER_1_WINS;
            if (player2Score > player1Score) return GameOutcome.PLAYER_2_WINS;
            return GameOutcome.DRAW; // Both reached winning score and are tied
        }


        // 2. Check for game termination based on turns (if no score-based winner yet)
        if (currentTurn >= maxTurns) {
            if (player1Score > player2Score) {
                return GameOutcome.PLAYER_1_WINS; // Max turns reached, Player 1 leads
            } else if (player2Score > player1Score) {
                return GameOutcome.PLAYER_2_WINS; // Max turns reached, Player 2 leads
            } else {
                return GameOutcome.DRAW; // Max turns reached, scores are tied
            }
        }

        // 3. If no win, loss, or draw condition is met, the game is ongoing
        return GameOutcome.ONGOING;
    }

    // Example usage (not part of the required output, but for context)
    public static void main(String[] args) {
        // Game 1: Ongoing
        GameState state1 = new GameState(10, 8, 5, 10, 20, "Player1");
        System.out.println("State 1 Outcome: " + checkGameOutcome(state1)); // ONGOING

        // Game 2: Player 1 Wins by score
        GameState state2 = new GameState(25, 15, 7, 10, 20, "Player2");
        System.out.println("State 2 Outcome: " + checkGameOutcome(state2)); // PLAYER_1_WINS

        // Game 3: Player 2 Wins by score
        GameState state3 = new GameState(18, 22, 6, 10, 20, "Player1");
        System.out.println("State 3 Outcome: " + checkGameOutcome(state3)); // PLAYER_2_WINS

        // Game 4: Draw by turns
        GameState state4 = new GameState(15, 15, 10, 10, 20, "Player1");
        System.out.println("State 4 Outcome: " + checkGameOutcome(state4)); // DRAW

        // Game 5: Player 1 Wins by turns (higher score)
        GameState state5 = new GameState(18, 16, 10, 10, 20, "Player2");
        System.out.println("State 5 Outcome: " + checkGameOutcome(state5)); // PLAYER_1_WINS

        // Game 6: Simultaneous win threshold reached, Player 1 has higher score
        GameState state6 = new GameState(25, 20, 8, 10, 20, "Player1");
        System.out.println("State 6 Outcome: " + checkGameOutcome(state6)); // PLAYER_1_WINS

        // Game 7: Simultaneous win threshold reached, Player 2 has higher score
        GameState state7 = new GameState(20, 25, 8, 10, 20, "Player1");
        System.out.println("State 7 Outcome: " + checkGameOutcome(state7)); // PLAYER_2_WINS

        // Game 8: Simultaneous win threshold reached, tied score
        GameState state8 = new GameState(20, 20, 8, 10, 20, "Player1");
        System.out.println("State 8 Outcome: " + checkGameOutcome(state8)); // DRAW
    }
}
