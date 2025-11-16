import java.util.List;
import java.util.Objects;

/**
 * A GameConcept class defines the core idea and fundamental mechanics of a game.
 * It serves as a blueprint for game design and development.
 */
public class GameConcept {
    private String name;
    private String genre;
    private String premise; // Short description of the game's world/story
    private String coreLoop; // The main repetitive activity the player performs
    private List<String> keyMechanics; // Specific systems or actions (e.g., combat, crafting, puzzles)
    private String winCondition;
    private String lossCondition;
    private String targetAudience;

    /**
     * Constructs a new GameConcept with the specified details.
     *
     * @param name The official name of the game.
     * @param genre The primary genre of the game (e.g., RPG, FPS, Puzzle, Strategy).
     * @param premise A brief overview of the game's setting, story, or main goal.
     * @param coreLoop A description of the fundamental repetitive gameplay cycle.
     * @param keyMechanics A list of distinct gameplay mechanics.
     * @param winCondition The primary goal players must achieve to win the game.
     * @param lossCondition The primary condition under which players lose the game.
     * @param targetAudience The demographic the game is primarily designed for.
     */
    public GameConcept(String name, String genre, String premise, String coreLoop,
                       List<String> keyMechanics, String winCondition, String lossCondition,
                       String targetAudience) {
        this.name = Objects.requireNonNull(name, "Game name cannot be null.");
        this.genre = Objects.requireNonNull(genre, "Game genre cannot be null.");
        this.premise = Objects.requireNonNull(premise, "Game premise cannot be null.");
        this.coreLoop = Objects.requireNonNull(coreLoop, "Game core loop cannot be null.");
        this.keyMechanics = Objects.requireNonNull(keyMechanics, "Game key mechanics list cannot be null.");
        this.winCondition = Objects.requireNonNull(winCondition, "Game win condition cannot be null.");
        this.lossCondition = Objects.requireNonNull(lossCondition, "Game loss condition cannot be null.");
        this.targetAudience = Objects.requireNonNull(targetAudience, "Game target audience cannot be null.");
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getPremise() {
        return premise;
    }

    public String getCoreLoop() {
        return coreLoop;
    }

    public List<String> getKeyMechanics() {
        return keyMechanics;
    }

    public String getWinCondition() {
        return winCondition;
    }

    public String getLossCondition() {
        return lossCondition;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    // --- Setters (optional, depending on whether the concept can change after creation) ---
    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setGenre(String genre) {
        this.genre = Objects.requireNonNull(genre);
    }

    public void setPremise(String premise) {
        this.premise = Objects.requireNonNull(premise);
    }

    public void setCoreLoop(String coreLoop) {
        this.coreLoop = Objects.requireNonNull(coreLoop);
    }

    public void setKeyMechanics(List<String> keyMechanics) {
        this.keyMechanics = Objects.requireNonNull(keyMechanics);
    }

    public void setWinCondition(String winCondition) {
        this.winCondition = Objects.requireNonNull(winCondition);
    }

    public void setLossCondition(String lossCondition) {
        this.lossCondition = Objects.requireNonNull(lossCondition);
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = Objects.requireNonNull(targetAudience);
    }

    @Override
    public String toString() {
        return "--- Game Concept: " + name + " ---\n" +
               "Genre: " + genre + "\n" +
               "Premise: " + premise + "\n" +
               "Core Loop: " + coreLoop + "\n" +
               "Key Mechanics: " + String.join(", ", keyMechanics) + "\n" +
               "Win Condition: " + winCondition + "\n" +
               "Loss Condition: " + lossCondition + "\n" +
               "Target Audience: " + targetAudience + "\n" +
               "------------------------------------";
    }
}
