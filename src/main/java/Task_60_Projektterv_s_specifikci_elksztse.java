import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Enum for task status
enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    ON_HOLD,
    CANCELLED
}

// Class representing a deliverable or a sub-section of the specification
class Deliverable {
    private String name;
    private String description;
    private boolean completed;

    public Deliverable(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }

    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return "  - " + name + " (" + (completed ? "Elkészült" : "Függőben") + ")";
    }
}

/**
 * Java osztály a "Projektterv és specifikáció elkészítése" feladat kezelésére.
 * Ez az osztály szimulálja a feladatot, annak állapotát és a főbb lépéseit.
 */
public class ProjectPlanAndSpecificationTask {
    private String taskName;
    private String projectId;
    private String responsiblePerson;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskStatus status;
    private List<Deliverable> deliverables; // A feladat részletei/kimenetei

    public ProjectPlanAndSpecificationTask(String projectId, String responsiblePerson, LocalDate dueDate) {
        this.taskName = "Projektterv és specifikáció elkészítése";
        this.projectId = projectId;
        this.responsiblePerson = responsiblePerson;
        this.startDate = LocalDate.now();
        this.dueDate = dueDate;
        this.status = TaskStatus.NOT_STARTED;
        this.deliverables = new ArrayList<>();
        initializeDeliverables();
    }

    // Inicializálja a projektterv és specifikáció főbb részeit/kimeneteit
    private void initializeDeliverables() {
        deliverables.add(new Deliverable("Projekt áttekintés", "Projekt célok, hatókör és kulcsfontosságú érdekelt felek meghatározása."));
        deliverables.add(new Deliverable("Követelmény specifikáció", "Funkcionális és nem funkcionális követelmények részletes leírása."));
        deliverables.add(new Deliverable("Munkabontási struktúra (WBS)", "A projekt feladatainak hierarchikus lebontása."));
        deliverables.add(new Deliverable("Ütemterv és mérföldkövek", "A projekt főbb fázisainak és határidőinek meghatározása."));
        deliverables.add(new Deliverable("Erőforrás terv", "Szükséges emberi és technikai erőforrások azonosítása."));
        deliverables.add(new Deliverable("Kockázatkezelési terv", "Potenciális kockázatok azonosítása és enyhítési stratégiák."));
        deliverables.add(new Deliverable("Kommunikációs terv", "Az érdekelt felek közötti kommunikáció módjainak meghatározása."));
        deliverables.add(new Deliverable("Technikai specifikáció", "A rendszer architektúrájának és technológiai stackjének részletes leírása."));
    }

    /**
     * Elindítja a projektterv és specifikáció elkészítésének folyamatát.
     * Szimulálja a részfeladatok elvégzését és frissíti az állapotot.
     */
    public void startTask() {
        if (this.status == TaskStatus.NOT_STARTED || this.status == TaskStatus.ON_HOLD) {
            System.out.println(taskName + " elindítva (" + projectId + ") " + responsiblePerson + " által.");
            this.status = TaskStatus.IN_PROGRESS;
            System.out.println("Aktuális állapot: " + this.status);

            // Szimulálja a részfeladatok elvégzését
            for (Deliverable del : deliverables) {
                System.out.println("  Dolgozunk a(z): '" + del.getName() + "' részen...");
                // Itt lehetne valós munkafolyamatot vagy időzítést szimulálni
                del.markCompleted(); // Jelölje meg teljesítettként
                System.out.println("  '" + del.getName() + "' elkészült.");
            }
            completeTask();
        } else {
            System.out.println("A feladat '" + taskName + "' (" + projectId + ") már '" + this.status + "' állapotban van.");
        }
    }

    /**
     * Befejezi a feladatot, ha minden részfeladat elkészült.
     */
    private void completeTask() {
        boolean allDeliverablesCompleted = deliverables.stream().allMatch(Deliverable::isCompleted);
        if (allDeliverablesCompleted) {
            this.status = TaskStatus.COMPLETED;
            System.out.println("\nA feladat '" + taskName + "' (" + projectId + ") sikeresen befejeződött!");
            System.out.println("Végső állapot: " + this.status);
        } else {
            System.out.println("Figyelem: Nem minden részfeladat készült el. A feladat még 'IN_PROGRESS' állapotban van.");
        }
    }

    // Getterek
    public String getTaskName() { return taskName; }
    public String getProjectId() { return projectId; }
    public String getResponsiblePerson() { return responsiblePerson; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public TaskStatus getStatus() { return status; }
    public List<Deliverable> getDeliverables() { return deliverables; }

    // Setterek (állapot frissítésére)
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setResponsiblePerson(String responsiblePerson) { this.responsiblePerson = responsiblePerson; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Projektterv és Specifikáció Feladat:\n");
        sb.append("  Projekt ID: ").append(projectId).append("\n");
        sb.append("  Feladat neve: ").append(taskName).append("\n");
        sb.append("  Felelős: ").append(responsiblePerson).append("\n");
        sb.append("  Kezdő dátum: ").append(startDate).append("\n");
        sb.append("  Határidő: ").append(dueDate).append("\n");
        sb.append("  Státusz: ").append(status).append("\n");
        sb.append("  Kimenetek/Részfeladatok:\n");
        deliverables.forEach(sb::append);
        return sb.toString();
    }

    // Fő metódus a demonstrációhoz
    public static void main(String[] args) {
        ProjectPlanAndSpecificationTask projectTask = new ProjectPlanAndSpecificationTask(
            "PROJ-2023-001",
            "Nagy Gábor (Projektmenedzser)",
            LocalDate.of(2024, 1, 31)
        );

        System.out.println("--- Kezdeti állapot ---\n" + projectTask + "\n");

        projectTask.startTask();

        System.out.println("\n--- Befejezés utáni állapot ---\n" + projectTask);
    }
}
