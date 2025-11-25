public class ScoreManager {
    private int score = 0;

    public void updateScore(int points) {
        this.score += points;
    }

    public void displayScore() {
        System.out.println("Pontszám: " + this.score);
    }

    public static void main(String[] args) {
        ScoreManager gameScore = new ScoreManager();
        gameScore.displayScore(); // Kezdeti pontszám: 0

        gameScore.updateScore(100);
        gameScore.displayScore(); // Pontszám frissítve: 100

        gameScore.updateScore(50);
        gameScore.displayScore(); // Pontszám frissítve: 150

        gameScore.updateScore(-20); // Pontszám csökkentése
        gameScore.displayScore(); // Pontszám frissítve: 130
    }
}
