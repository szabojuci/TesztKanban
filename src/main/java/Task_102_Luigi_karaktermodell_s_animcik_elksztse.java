public class LuigiCharacterCreationTask {
    private String taskDescription;
    private boolean modelAndRigCompleted;
    private int completedAnimations;
    private final int totalAnimationsExpected;
    private String currentStatusMessage;

    /**
     * Létrehoz egy feladatkezelő példányt Luigi karaktermodell és animációk készítéséhez.
     *
     * @param totalAnimationsExpected A karakterhez szükséges animációk teljes száma.
     */
    public LuigiCharacterCreationTask(int totalAnimationsExpected) {
        this.taskDescription = "Luigi karaktermodell és animációk elkészítése";
        this.modelAndRigCompleted = false;
        this.completedAnimations = 0;
        this.totalAnimationsExpected = Math.max(1, totalAnimationsExpected); // Minimum 1 animáció elvárása
        this.currentStatusMessage = "Feladat indítása: Modell és animációk előkészítése.";
    }

    /**
     * Jelzi, hogy a Luigi karaktermodell és a rig elkészült.
     */
    public void completeModelAndRig() {
        this.modelAndRigCompleted = true;
        this.currentStatusMessage = "Luigi modell és rig elkészült.";
    }

    /**
     * Hozzáad egy elkészült animációt a feladathoz.
     * Csak akkor adható hozzá animáció, ha a modell és a rig már elkészült.
     *
     * @param animationName Az elkészült animáció neve (pl. "Idle", "Walk", "Jump").
     */
    public void addCompletedAnimation(String animationName) {
        if (!modelAndRigCompleted) {
            this.currentStatusMessage = "Modell és rig még nem készült el. Animáció hozzáadása sikertelen.";
            return;
        }
        if (completedAnimations < totalAnimationsExpected) {
            completedAnimations++;
            this.currentStatusMessage = String.format("Animáció hozzáadva: '%s'. Jelenleg %d/%d animáció kész.",
                                                        animationName, completedAnimations, totalAnimationsExpected);
        } else {
            this.currentStatusMessage = "Minden animáció elkészült.";
        }
    }

    /**
     * Kiszámolja a feladat teljes progressz százalékát.
     * A modell és rig elkészülése 40%-ot, az animációk elkészülése 60%-ot képvisel.
     *
     * @return A feladat teljes progressz százaléka (0.0-100.0).
     */
    public double getOverallProgressPercentage() {
        double modelWeight = 0.4;
        double animationWeight = 0.6;

        double progress = 0.0;
        if (modelAndRigCompleted) {
            progress += modelWeight;
        }
        if (totalAnimationsExpected > 0) {
            progress += animationWeight * ((double) completedAnimations / totalAnimationsExpected);
        }
        return Math.min(100.0, progress * 100.0);
    }

    /**
     * Ellenőrzi, hogy a feladat teljesen befejeződött-e (modell és összes animáció kész).
     *
     * @return Igaz, ha a feladat befejeződött, egyébként hamis.
     */
    public boolean isTaskCompleted() {
        return modelAndRigCompleted && (completedAnimations >= totalAnimationsExpected);
    }

    /**
     * Visszaadja a feladat aktuális állapotáról szóló részletes jelentést.
     *
     * @return Egy formázott String, amely a feladat aktuális állapotát mutatja.
     */
    public String getStatusReport() {
        return String.format("Feladat: %s\nÁllapot: %s\nModell és rig kész: %b\nElkészült animációk: %d/%d\nÖsszesített progressz: %.2f%%",
                taskDescription, currentStatusMessage, modelAndRigCompleted, completedAnimations, totalAnimationsExpected, getOverallProgressPercentage());
    }
}
