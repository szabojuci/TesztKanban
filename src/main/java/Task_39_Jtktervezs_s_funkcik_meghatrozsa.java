import java.util.List;
import java.util.ArrayList;

public class GameDesignDocument {
    private String gameTitle;
    private String genre;
    private String coreConcept;
    private String targetAudience;
    private String coreGameplayLoop;
    private List<String> keyFeatures;
    private String monetizationStrategy;
    private String artStyle;

    /**
     * Konstruktor a játéktérvezési dokumentum alapvető paramétereinek beállításához.
     * Ez a konstruktor magában foglalja a játéktervezés és a főbb funkciók definícióját.
     *
     * @param gameTitle A játék címe.
     * @param genre A játék műfaja (pl. RPG, Puzzle, FPS).
     * @param coreConcept A játék alapkoncepciója, egy rövid leírás.
     * @param targetAudience A célközönség (pl. casual játékosok, hardcore gamerek).
     * @param coreGameplayLoop Az alapvető játékmenet ciklusának leírása.
     * @param keyFeatures A játék kulcsfontosságú funkcióinak listája.
     * @param monetizationStrategy A monetizációs stratégia (pl. F2P, B2P, előfizetés).
     * @param artStyle A játék művészeti stílusa (pl. pixel art, realisztikus, cel-shaded).
     */
    public GameDesignDocument(String gameTitle, String genre, String coreConcept, String targetAudience,
                              String coreGameplayLoop, List<String> keyFeatures,
                              String monetizationStrategy, String artStyle) {
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.coreConcept = coreConcept;
        this.targetAudience = targetAudience;
        this.coreGameplayLoop = coreGameplayLoop;
        this.keyFeatures = new ArrayList<>(keyFeatures); // Védelmi másolat
        this.monetizationStrategy = monetizationStrategy;
        this.artStyle = artStyle;
    }

    /**
     * Meghatározza és összefoglalja a játéktervezést és a funkciókat egy formázott szövegben.
     * Ez a metódus képviseli a "Játéktervezés és funkciók meghatározása" feladat megoldását.
     *
     * @return Egy formázott string, amely tartalmazza a játéktervezés és a kulcsfontosságú funkciók definícióját.
     */
    public String defineGameDesign() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Játéktervezési Dokumentum: ").append(gameTitle).append(" ---\n");
        sb.append("Műfaj: ").append(genre).append("\n");
        sb.append("Alapkoncepció: ").append(coreConcept).append("\n");
        sb.append("Célközönség: ").append(targetAudience).append("\n");
        sb.append("Alapvető Játékmenet Ciklus: ").append(coreGameplayLoop).append("\n");
        sb.append("Kulcsfontosságú Funkciók:\n");
        if (keyFeatures.isEmpty()) {
            sb.append("  - Nincsenek meghatározott funkciók.\n");
        } else {
            for (String feature : keyFeatures) {
                sb.append("  - ").append(feature).append("\n");
            }
        }
        sb.append("Monetizációs Stratégia: ").append(monetizationStrategy).append("\n");
        sb.append("Művészeti Stílus: ").append(artStyle).append("\n");
        sb.append("------------------------------------------\n");
        return sb.toString();
    }

    // Példa használat (ez a rész nem része a kimeneti követelménynek, csak illusztráció)
    /*
    public static void main(String[] args) {
        List<String> features = new ArrayList<>();
        features.add("Innovatív kártyacsaták");
        features.add("Roguelike kampány");
        features.add("Karakterfejlesztés");
        features.add("Döntésalapú narratíva");

        GameDesignDocument myGame = new GameDesignDocument(
            "Shadowrealm Legends",
            "Deckbuilding Roguelike",
            "Egy sötét fantasy világban játszódó kártyás roguelike, ahol minden döntés számít.",
            "Stratégiai és kártyajátékok kedvelői",
            "Küldetések felvétele -> Harc -> Kártyák gyűjtése/fejlesztése -> Továbbjutás",
            features,
            "Prémium játék (B2P) DLC-kkel",
            "Gótikus, kézzel rajzolt stílus"
        );

        System.out.println(myGame.defineGameDesign());
    }
    */
}
