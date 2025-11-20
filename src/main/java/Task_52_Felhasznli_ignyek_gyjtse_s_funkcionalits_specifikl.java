import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Kezeli a felhasználói igények gyűjtését és a funkcionalitás specifikálását.
 * Ez az osztály szimulálja a folyamatot, ahol a nyers igényekből
 * konkrét, specifikált funkcionalitások jönnek létre.
 */
public class RequirementsManager {

    private final List<Requirement> rawRequirements;
    private final List<FunctionalitySpec> functionalSpecifications;

    public RequirementsManager() {
        this.rawRequirements = new ArrayList<>();
        this.functionalSpecifications = new ArrayList<>();
    }

    /**
     * Felhasználói igény rögzítése.
     * @param description Az igény rövid leírása.
     * @param source Az igény forrása (pl. "Felhasználói interjú", "Stakeholder megbeszélés").
     */
    public void gatherRequirement(String description, String source) {
        Requirement newReq = new Requirement(UUID.randomUUID().toString(), description, source, Requirement.Status.GATHERED);
        rawRequirements.add(newReq);
        System.out.println("Igény rögzítve: '" + description + "' forrás: " + source);
    }

    /**
     * A rögzített nyers igények alapján funkcionalitás specifikálása.
     * Ez a metódus a nyers igényeket feldolgozza és részletes specifikációkat generál.
     * Valós környezetben ez sokkal komplexebb elemzést és finomítást igényelne.
     * @return A generált funkcionalitási specifikációk listája.
     */
    public List<FunctionalitySpec> specifyFunctionality() {
        System.out.println("\n" + rawRequirements.size() + " nyers igény elemzése és specifikálása...");
        functionalSpecifications.clear(); // Előző specifikációk törlése

        for (Requirement req : rawRequirements) {
            // Egyszerű átalakítás: minden nyers igényből egy alap funkcionalitási specifikáció lesz.
            String specId = "FS-" + UUID.randomUUID().toString().substring(0, 8);
            String specDescription = "Implementálja a funkcionalitást a következő igény alapján: '" + req.getDescription() + "'";
            String acceptanceCriteria = "Ellenőrizze, hogy a következő teljesül: " + req.getDescription() + ".";
            FunctionalitySpec spec = new FunctionalitySpec(specId, specDescription, acceptanceCriteria, FunctionalitySpec.Priority.MEDIUM);
            functionalSpecifications.add(spec);
            System.out.println("  - Specifikálva: '" + spec.getDescription() + "' (ID: " + spec.getId() + ")");
        }
        System.out.println("Funkcionalitás specifikáció elkészült. " + functionalSpecifications.size() + " specifikáció definiálva.");
        return Collections.unmodifiableList(functionalSpecifications);
    }

    /**
     * Visszaadja a rögzített nyers igények listáját.
     */
    public List<Requirement> getRawRequirements() {
        return Collections.unmodifiableList(rawRequirements);
    }

    /**
     * Visszaadja a specifikált funkcionalitások listáját.
     */
    public List<FunctionalitySpec> getFunctionalSpecifications() {
        return Collections.unmodifiableList(functionalSpecifications);
    }

    /**
     * Belső osztály egy felhasználói igény reprezentálására.
     */
    public static class Requirement {
        public enum Status { GATHERED, PENDING_REVIEW, APPROVED, REJECTED }
        private final String id;
        private final String description;
        private final String source;
        private final Status status;

        public Requirement(String id, String description, String source, Status status) {
            this.id = id;
            this.description = description;
            this.source = source;
            this.status = status;
        }

        public String getId() { return id; }
        public String getDescription() { return description; }
        public String getSource() { return source; }
        public Status getStatus() { return status; }

        @Override
        public String toString() {
            return "Requirement{" + "id='" + id + '\'' + ", description='" + description + '\'' + ", source='" + source + '\'' + ", status=" + status + '}';
        }
    }

    /**
     * Belső osztály egy specifikált funkcionalitás reprezentálására.
     */
    public static class FunctionalitySpec {
        public enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
        private final String id;
        private final String description;
        private final String acceptanceCriteria;
        private final Priority priority;

        public FunctionalitySpec(String id, String description, String acceptanceCriteria, Priority priority) {
            this.id = id;
            this.description = description;
            this.acceptanceCriteria = acceptanceCriteria;
            this.priority = priority;
        }

        public String getId() { return id; }
        public String getDescription() { return description; }
        public String getAcceptanceCriteria() { return acceptanceCriteria; }
        public Priority getPriority() { return priority; }

        @Override
        public String toString() {
            return "FunctionalitySpec{" + "id='" + id + '\'' + ", description='" + description + '\'' + ", acceptanceCriteria='" + acceptanceCriteria + '\'' + ", priority=" + priority + '}';
        }
    }

    // Példa használatra (main metódus egy külön osztályban is lehetne)
    public static void main(String[] args) {
        RequirementsManager manager = new RequirementsManager();

        // 1. Igények gyűjtése
        manager.gatherRequirement("A felhasználó be tudjon jelentkezni email címmel és jelszóval.", "Felhasználói interjú");
        manager.gatherRequirement("A rendszer tárolja a felhasználó kedvenc termékeit.", "Termékmenedzser");
        manager.gatherRequirement("A kosárban lévő termékek száma látható legyen a fejlécben.", "UX Designer");

        // 2. Funkcionalitás specifikálása
        List<FunctionalitySpec> specs = manager.specifyFunctionality();

        System.out.println("\n--- Összes specifikáció ---");
        specs.forEach(System.out::println);
    }
}
