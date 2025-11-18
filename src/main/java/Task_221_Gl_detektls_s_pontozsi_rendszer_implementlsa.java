import java.util.HashMap;
import java.util.Map;

public class GoalScoringSystem {

    public enum Team {
        TEAM_A,
        TEAM_B
    }

    private final Map<Team, Integer> scores;

    public GoalScoringSystem() {
        scores = new HashMap<>();
        scores.put(Team.TEAM_A, 0);
        scores.put(Team.TEAM_B, 0);
    }

    /**
     * Detects a goal for the specified team and increments their score.
     * This method simulates the detection and scoring process.
     * @param scoringTeam The team that scored the goal.
     */
    public synchronized void scoreGoal(Team scoringTeam) {
        if (scores.containsKey(scoringTeam)) {
            scores.put(scoringTeam, scores.get(scoringTeam) + 1);
            System.out.println("GÓL! " + scoringTeam + " szerzett pontot.");
            displayScores();
        } else {
            System.err.println("Ismeretlen csapat: " + scoringTeam);
        }
    }

    /**
     * Returns the current score for a specific team.
     * @param team The team whose score is requested.
     * @return The current score of the team, or 0 if the team is not found.
     */
    public int getScore(Team team) {
        return scores.getOrDefault(team, 0);
    }

    /**
     * Displays the current scores for all teams to the console.
     */
    public void displayScores() {
        System.out.println("--- Jelenlegi állás ---");
        for (Map.Entry<Team, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("-----------------------");
    }

    /**
     * Resets all team scores to zero.
     */
    public synchronized void resetScores() {
        scores.put(Team.TEAM_A, 0);
        scores.put(Team.TEAM_B, 0);
        System.out.println("A pontok nullázva lettek.");
        displayScores();
    }

    public static void main(String[] args) {
        GoalScoringSystem system = new GoalScoringSystem();

        System.out.println("Játék indítása...");
        system.displayScores();

        // Szimulált gólok
        system.scoreGoal(Team.TEAM_A);
        system.scoreGoal(Team.TEAM_A);
        system.scoreGoal(Team.TEAM_B);
        system.scoreGoal(Team.TEAM_A);
        system.scoreGoal(Team.TEAM_B);

        System.out.println("\nJáték vége.");
        System.out.println("Végeredmény:");
        System.out.println(Team.TEAM_A + ": " + system.getScore(Team.TEAM_A));
        System.out.println(Team.TEAM_B + ": " + system.getScore(Team.TEAM_B));

        // Új játék indítása
        system.resetScores();
        system.scoreGoal(Team.TEAM_B);
    }
}
