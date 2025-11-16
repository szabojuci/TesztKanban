import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class GameDesignDocument {

    private String gameTitle;
    private String highConcept;
    private String genre;
    private String targetAudience;
    private String coreGameplayLoop;
    private List<String> keyFeatures;
    private Map<String, String> mechanics; // e.g., "Movement": "WASD to move, Space to jump"
    private String storySummary;
    private List<String> mainCharacters;
    private String artStyle;
    private String audioDirection;
    private String technicalOverview; // e.g., platform, engine, tools
    private String monetizationStrategy; // if applicable
    private String developmentTeam; // Roles and responsibilities

    /**
     * Konstruktor a GameDesignDocument létrehozásához.
     *
     * @param gameTitle A játék címe.
     * @param highConcept A játék rövid, átfogó leírása.
     * @param genre A játék műfaja.
     * @param targetAudience A célközönség leírása.
     * @param coreGameplayLoop A játékmenet alapvető ciklusa.
     * @param keyFeatures A játék kulcsfontosságú jellemzői.
     * @param mechanics A játék mechanikái (pl. mozgás, harc).
     * @param storySummary A történet összefoglalója.
     * @param mainCharacters A főbb karakterek listája.
     * @param artStyle A vizuális stílus leírása.
     * @param audioDirection Az audio irányának leírása.
     * @param technicalOverview A technikai áttekintés (platform, motor stb.).
     * @param monetizationStrategy A monetizációs stratégia (pl. prémium, F2P, DLC).
     * @param developmentTeam A fejlesztőcsapat és szerepek.
     */
    public GameDesignDocument(String gameTitle, String highConcept, String genre, String targetAudience,
                              String coreGameplayLoop, List<String> keyFeatures, Map<String, String> mechanics,
                              String storySummary, List<String> mainCharacters, String artStyle,
                              String audioDirection, String technicalOverview, String monetizationStrategy,
                              String developmentTeam) {
        this.gameTitle = gameTitle;
        this.highConcept = highConcept;
        this.genre = genre;
        this.targetAudience = targetAudience;
        this.coreGameplayLoop = coreGameplayLoop;
        this.keyFeatures = keyFeatures;
        this.mechanics = mechanics;
        this.storySummary = storySummary;
        this.mainCharacters = mainCharacters;
        this.artStyle = artStyle;
        this.audioDirection = audioDirection;
        this.technicalOverview = technicalOverview;
        this.monetizationStrategy = monetizationStrategy;
        this.developmentTeam = developmentTeam;
    }

    /**
     * Létrehozza a Játéktervezési Dokumentum (GDD) formázott szöveges tartalmát.
     * Ez a metódus a dokumentum tartalmának generálását szimulálja.
     *
     * @return Egy String, amely a GDD strukturált tartalmát tartalmazza.
     */
    public String generateDocumentContent() {
        StringJoiner sj = new StringJoiner("\n\n");

        sj.add("# Játéktervezési Dokumentum (GDD)");
        sj.add("---");

        sj.add("## 1. Alapkoncepció");
        sj.add("### 1.1 Játék címe: " + gameTitle);
        sj.add("### 1.2 Magas szintű koncepció: " + highConcept);
        sj.add("### 1.3 Műfaj: " + genre);
        sj.add("### 1.4 Célközönség: " + targetAudience);

        sj.add("## 2. Játékmenet");
        sj.add("### 2.1 Alapvető játékmenet ciklus: " + coreGameplayLoop);
        sj.add("### 2.2 Kulcsfontosságú funkciók:");
        keyFeatures.forEach(feature -> sj.add("    - " + feature));
        sj.add("### 2.3 Mechanikák:");
        mechanics.forEach((name, description) -> sj.add("    - " + name + ": " + description));

        sj.add("## 3. Történet és Világ");
        sj.add("### 3.1 Történet összefoglaló: " + storySummary);
        sj.add("### 3.2 Főbb karakterek:");
        mainCharacters.forEach(character -> sj.add("    - " + character));

        sj.add("## 4. Művészet és Hang");
        sj.add("### 4.1 Művészeti stílus: " + artStyle);
        sj.add("### 4.2 Audio irány: " + audioDirection);

        sj.add("## 5. Technikai áttekintés");
        sj.add("### 5.1 Technikai részletek: " + technicalOverview);

        if (monetizationStrategy != null && !monetizationStrategy.isEmpty()) {
            sj.add("## 6. Monetizáció");
            sj.add("### 6.1 Stratégia: " + monetizationStrategy);
        }

        if (developmentTeam != null && !developmentTeam.isEmpty()) {
            sj.add("## 7. Fejlesztőcsapat");
            sj.add("### 7.1 Csapat és szerepek: " + developmentTeam);
        }

        sj.add("--- END OF DOCUMENT ---");
        return sj.toString();
    }

    // Opcionálisan getter metódusok is hozzáadhatók a mezőkhöz, ha szükséges.
    // Például:
    // public String getGameTitle() { return gameTitle; }
    // ...
}
