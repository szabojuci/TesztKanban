public class GameScoreManager {

    private static final int REGULAR_POINT_VALUE = 10;
    private static final int POWER_PELLET_VALUE = 50;
    private static final int GHOST_POINT_VALUE = 200;
    private static final long POWER_PELLET_DURATION_MS = 10 * 1000; // 10 seconds

    private int score;
    private boolean isPoweredUp;
    private long powerUpEndTime;

    public GameScoreManager() {
        this.score = 0;
        this.isPoweredUp = false;
        this.powerUpEndTime = 0;
    }

    /**
     * Adds points for eating a regular point.
     */
    public void eatRegularPoint() {
        score += REGULAR_POINT_VALUE;
    }

    /**
     * Adds points for eating a power pellet and activates the powered-up state.
     */
    public void eatPowerPellet() {
        score += POWER_PELLET_VALUE;
        isPoweredUp = true;
        powerUpEndTime = System.currentTimeMillis() + POWER_PELLET_DURATION_MS;
    }

    /**
     * Attempts to eat a ghost. Only succeeds if the player is currently powered up.
     *
     * @return true if a ghost was eaten and points were awarded, false otherwise.
     */
    public boolean eatGhost() {
        if (isPlayerPoweredUp()) { // Check state and potentially expire it
            score += GHOST_POINT_VALUE;
            return true;
        }
        return false;
    }

    /**
     * Checks if the player is currently in a powered-up state.
     * This method also handles the expiration of the power-up state.
     *
     * @return true if the player is powered up, false otherwise.
     */
    public boolean isPlayerPoweredUp() {
        if (isPoweredUp && System.currentTimeMillis() >= powerUpEndTime) {
            isPoweredUp = false; // Power-up has expired
        }
        return isPoweredUp;
    }

    /**
     * Returns the current score of the player.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets the game state (score and power-up status).
     */
    public void resetGame() {
        this.score = 0;
        this.isPoweredUp = false;
        this.powerUpEndTime = 0;
    }

    // Example usage (optional, for demonstration if this were a main class)
    /*
    public static void main(String[] args) throws InterruptedException {
        GameScoreManager game = new GameScoreManager();
        System.out.println("Initial score: " + game.getScore() + ", Powered up: " + game.isPlayerPoweredUp());

        game.eatRegularPoint();
        System.out.println("After regular point: " + game.getScore());

        game.eatPowerPellet();
        System.out.println("After power pellet: " + game.getScore() + ", Powered up: " + game.isPlayerPoweredUp());

        game.eatGhost();
        System.out.println("After eating ghost (powered up): " + game.getScore());

        System.out.println("Waiting for power-up to expire...");
        Thread.sleep(POWER_PELLET_DURATION_MS + 1000); // Wait a bit longer than duration

        System.out.println("Powered up: " + game.isPlayerPoweredUp()); // Should be false now
        game.eatGhost(); // This should fail
        System.out.println("After trying to eat ghost (not powered up): " + game.getScore());

        game.resetGame();
        System.out.println("After reset: " + game.getScore() + ", Powered up: " + game.isPlayerPoweredUp());
    }
    */
}
