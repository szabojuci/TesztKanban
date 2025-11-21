/**
 * A GameEngineType enum reprezentálja a különböző játékmotorokat,
 * alapvető tulajdonságaikkal és egy szimulált alapbeállítási folyamattal.
 */
public enum GameEngineType {
    UNITY("Unity 3D", "C#", "Asset Store, Komponens alapú, Kiterjedt ökoszisztéma", "Ingyenes (Personal), Fizetős (Pro)"),
    UNREAL_ENGINE("Unreal Engine", "C++", "Blueprints, Fotorealisztikus grafika, Nagy projektekre optimalizálva", "Ingyenes (bevételmegosztás)"),
    GODOT("Godot Engine", "GDScript, C#, C++", "Saját node-alapú rendszer, Könnyű, Nyílt forráskódú", "MIT Licenc (Ingyenes)"),
    LIBGDX("LibGDX", "Java", "Kódra fókuszáló, Platformfüggetlen keretrendszer", "Apache 2.0 (Ingyenes)");

    private final String name;
    private final String primaryLanguage;
    private final String keyFeatures;
    private final String licensing;

    GameEngineType(String name, String primaryLanguage, String keyFeatures, String licensing) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.keyFeatures = keyFeatures;
        this.licensing = licensing;
    }

    public String getName() { return name; }
    public String getPrimaryLanguage() { return primaryLanguage; }
    public String getKeyFeatures() { return keyFeatures; }
    public String getLicensing() { return licensing; }

    /**
     * Szimulálja a kiválasztott játékmotor alapbeállítási lépéseit.
     */
    public void performBasicSetup() {
        System.out.println("--- Alapbeállítások elvégzése a(z) " + name + " motorhoz ---");
        System.out.println("1. Új projekt létrehozása: 'MyAwesomeGame'");
        System.out.println("2. Célplatformok beállítása: Desktop, Mobil (Android/iOS)");
        System.out.println("3. Bemeneti rendszer alapértelmezett konfigurálása (egér, billentyűzet, gamepad).");
        System.out.println("4. Első jelenet/szint létrehozása ('MainScene').");
        System.out.println("5. Alapvető grafikai beállítások optimalizálása (pl. felbontás, minőség).");
        System.out.println("6. Verziókezelő inicializálása (pl. Git repository létrehozása).");
        System.out.println("Alapbeállítások sikeresen elvégezve a(z) " + name + " motorhoz.\n");
    }

    @Override
    public String toString() {
        return "Játékmotor: " + name +
               "\n  Elsődleges nyelv: " + primaryLanguage +
               "\n  Főbb jellemzők: " + keyFeatures +
               "\n  Licenc: " + licensing;
    }
}

/**
 * A GameEngineSetupManager osztály felelős a játékmotor kiválasztásának és
 * az alapbeállítások elvégzésének szimulált kezeléséért.
 */
public class GameEngineSetupManager {
    private GameEngineType selectedEngine;

    public GameEngineSetupManager() {
        this.selectedEngine = null; // Kezdetben nincs kiválasztott motor
    }

    /**
     * Kiválaszt egy játékmotort a megadott típus alapján.
     * @param type A kiválasztani kívánt játékmotor típusa.
     */
    public void selectEngine(GameEngineType type) {
        if (type == null) {
            System.out.println("Hiba: Érvénytelen játékmotor típus lett megadva.\n");
            return;
        }
        this.selectedEngine = type;
        System.out.println("Sikeresen kiválasztva a(z) " + type.getName() + " játékmotor.");
        System.out.println(type.toString() + "\n");
    }

    /**
     * Elvégzi az alapbeállításokat a jelenleg kiválasztott játékmotoron.
     * Ha nincs kiválasztva motor, figyelmeztetést ad.
     */
    public void performBasicSetupForSelectedEngine() {
        if (selectedEngine == null) {
            System.out.println("Figyelmeztetés: Nincs kiválasztva játékmotor. Kérem, válasszon előbb egy motort.\n");
            return;
        }
        selectedEngine.performBasicSetup();
    }

    /**
     * Visszaadja a jelenleg kiválasztott játékmotor típusát.
     * @return A kiválasztott játékmotor típusa, vagy null, ha nincs kiválasztva.
     */
    public GameEngineType getSelectedEngine() {
        return selectedEngine;
    }

    /**
     * Fő metódus a demonstrációhoz.
     */
    public static void main(String[] args) {
        GameEngineSetupManager manager = new GameEngineSetupManager();

        System.out.println("--- Játékmotor kiválasztása és alapbeállítások elvégzése ---");

        // 1. Próbálkozás beállítással kiválasztott motor nélkül
        manager.performBasicSetupForSelectedEngine();

        // 2. Unity játékmotor kiválasztása és beállítása
        manager.selectEngine(GameEngineType.UNITY);
        manager.performBasicSetupForSelectedEngine();

        // 3. Godot játékmotor kiválasztása és beállítása
        manager.selectEngine(GameEngineType.GODOT);
        manager.performBasicSetupForSelectedEngine();

        // 4. Unreal Engine játékmotor kiválasztása és beállítása
        manager.selectEngine(GameEngineType.UNREAL_ENGINE);
        manager.performBasicSetupForSelectedEngine();

        // 5. Hibás kiválasztás tesztelése
        manager.selectEngine(null);
    }
}
