import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A GameDefinition osztály a játék alapvető követelményeinek és funkcióinak
 * meghatározására szolgál.
 * Lehetővé teszi a játék nevének, kulcsfontosságú követelményeinek és
 * specifikus funkcióinak tárolását.
 */
public class GameDefinition {
    private String gameName;
    private List<String> coreRequirements;
    private List<String> features;

    /**
     * Létrehoz egy új GameDefinition példányt a megadott játéknévvel.
     *
     * @param gameName A játék neve. Nem lehet null vagy üres.
     * @throws IllegalArgumentException Ha a játéknév null vagy üres.
     */
    public GameDefinition(String gameName) {
        if (gameName == null || gameName.trim().isEmpty()) {
            throw new IllegalArgumentException("A játék neve nem lehet null vagy üres.");
        }
        this.gameName = gameName;
        this.coreRequirements = new ArrayList<>();
        this.features = new ArrayList<>();
    }

    /**
     * Hozzáad egy alapvető követelményt a játékhoz.
     *
     * @param requirement Az alapvető követelmény leírása.
     */
    public void addCoreRequirement(String requirement) {
        if (requirement != null && !requirement.trim().isEmpty()) {
            this.coreRequirements.add(requirement.trim());
        }
    }

    /**
     * Hozzáad egy funkciót a játékhoz.
     *
     * @param feature A funkció leírása.
     */
    public void addFeature(String feature) {
        if (feature != null && !feature.trim().isEmpty()) {
            this.features.add(feature.trim());
        }
    }

    /**
     * Visszaadja a játék nevét.
     *
     * @return A játék neve.
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Visszaadja a játék alapvető követelményeinek listáját.
     * A visszaadott lista nem módosítható, így megakadályozva a belső állapot
     * véletlen módosítását kívülről.
     *
     * @return A alapvető követelmények nem módosítható listája.
     */
    public List<String> getCoreRequirements() {
        return Collections.unmodifiableList(coreRequirements);
    }

    /**
     * Visszaadja a játék funkcióinak listáját.
     * A visszaadott lista nem módosítható, így megakadályozva a belső állapot
     * véletlen módosítását kívülről.
     *
     * @return A funkciók nem módosítható listája.
     */
    public List<String> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    /**
     * Visszaadja az objektum sztring reprezentációját.
     *
     * @return A GameDefinition sztring reprezentációja.
     */
    @Override
    public String toString() {
        return "GameDefinition {" +
               "\n  Játék neve: '" + gameName + '\'' +
               ",\n  Alapvető követelmények: " + coreRequirements +
               ",\n  Funkciók: " + features +
               "\n}";
    }

    /**
     * Összehasonlítja ezt a GameDefinition objektumot egy másik objektummal
     * egyenlőség szempontjából.
     *
     * @param o Az összehasonlítandó objektum.
     * @return Igaz, ha az objektumok egyenlőek, különben hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDefinition that = (GameDefinition) o;
        return Objects.equals(gameName, that.gameName) &&
               Objects.equals(coreRequirements, that.coreRequirements) &&
               Objects.equals(features, that.features);
    }

    /**
     * Visszaadja az objektum hash kód értékét.
     *
     * @return Az objektum hash kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(gameName, coreRequirements, features);
    }
}
