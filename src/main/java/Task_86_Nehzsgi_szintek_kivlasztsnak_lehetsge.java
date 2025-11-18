public class DifficultyManager {

    /**
     * Enum representing different difficulty levels with associated properties.
     */
    public enum Difficulty {
        EASY("Könnyű", 0.5),
        NORMAL("Normál", 1.0),
        HARD("Nehéz", 1.5),
        INSANE("Őrült", 2.0);

        private final String displayName;
        private final double multiplier; // E.g., for score, enemy health, etc.

        Difficulty(String displayName, double multiplier) {
            this.displayName = displayName;
            this.multiplier = multiplier;
        }

        public String getDisplayName() {
            return displayName;
        }

        public double getMultiplier() {
            return multiplier;
        }

        /**
         * Converts a string representation (enum name or display name) to a Difficulty enum.
         * Useful for user input.
         *
         * @param text The string to parse.
         * @return The corresponding Difficulty enum.
         * @throws IllegalArgumentException if no matching difficulty is found.
         */
        public static Difficulty fromString(String text) {
            for (Difficulty d : Difficulty.values()) {
                if (d.name().equalsIgnoreCase(text) || d.displayName.equalsIgnoreCase(text)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("Nincs ilyen nehézségi szint: " + text);
        }
    }

    private Difficulty currentDifficulty;

    /**
     * Initializes the DifficultyManager with a default difficulty.
     */
    public DifficultyManager() {
        this.currentDifficulty = Difficulty.NORMAL; // Alapértelmezett nehézségi szint
    }

    /**
     * Returns the currently selected difficulty.
     *
     * @return The current Difficulty enum.
     */
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    /**
     * Sets the new difficulty level.
     *
     * @param newDifficulty The Difficulty enum to set.
     * @throws IllegalArgumentException if newDifficulty is null.
     */
    public void setDifficulty(Difficulty newDifficulty) {
        if (newDifficulty == null) {
            throw new IllegalArgumentException("A nehézségi szint nem lehet null.");
        }
        this.currentDifficulty = newDifficulty;
    }

    /**
     * Main method for demonstration of difficulty selection.
     */
    public static void main(String[] args) {
        DifficultyManager manager = new DifficultyManager();
        System.out.println("Alapértelmezett nehézség: " + manager.getCurrentDifficulty().getDisplayName() +
                           " (Szorzó: " + manager.getCurrentDifficulty().getMultiplier() + ")");

        System.out.println("\nElérhető nehézségi szintek:");
        for (Difficulty d : Difficulty.values()) {
            System.out.println("- " + d.getDisplayName() + " (" + d.name() + ", Szorzó: " + d.getMultiplier() + ")");
        }

        // Nehézségi szint beállítása enum konstans alapján
        manager.setDifficulty(Difficulty.HARD);
        System.out.println("\nBeállított nehézség (HARD): " + manager.getCurrentDifficulty().getDisplayName());

        // Nehézségi szint beállítása string bemenet alapján (pl. felhasználói felületről)
        try {
            Difficulty selectedFromInput = Difficulty.fromString("könnyű");
            manager.setDifficulty(selectedFromInput);
            System.out.println("Beállított nehézség ('könnyű' inputból): " + manager.getCurrentDifficulty().getDisplayName());
        } catch (IllegalArgumentException e) {
            System.err.println("Hiba a nehézségi szint beállításakor: " + e.getMessage());
        }

        // Érvénytelen bemenet kezelése
        try {
            Difficulty invalidInput = Difficulty.fromString("SzuperNehéz");
            manager.setDifficulty(invalidInput); // Ez a sor nem fog lefutni
        } catch (IllegalArgumentException e) {
            System.err.println("Hiba az érvénytelen nehézségi szint beállításakor: " + e.getMessage());
        }
    }
}
