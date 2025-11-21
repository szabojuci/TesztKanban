import java.util.ArrayList;
import java.util.List;

/**
 * A GameDesignDocument osztály egy játéktervezési dokumentumot (GDD) reprezentál,
 * összefoglalva a játék kulcsfontosságú aspektusait.
 * Célja egy komplett, de tömör áttekintés nyújtása a játék koncepciójáról,
 * mechanikáiról, narratívájáról és művészeti irányáról.
 */
public class GameDesignDocument {

    // --- Általános Információk ---
    private String cim;
    private String mufaj;
    private String platformok;
    private String celkozonseg;
    private String logline; // Egy mondatos összefoglaló a játékról

    // --- Alapkoncepció ---
    private String egyediEladasiPontok; // Mi teszi a játékot különlegessé?
    private String jatekmenetHurok; // A játék alapvető cselekvési ciklusa (pl. "Fedezd fel -> Harcolj -> Lootolj -> Fejlessz")

    // --- Narratíva és Világ ---
    private String sztoriSzintezis; // A történet rövid összefoglalása
    private List<String> foKarakterek; // Főbb karakterek listája (név, szerep)
    private String vilagEpites; // A játék világának rövid leírása

    // --- Játékmechanika ---
    private List<String> alapMechanikak; // Fő játékmechanikák (pl. harc, felfedezés, crafting)
    private String iranyitas; // Az irányítás alapvető leírása
    private String progresszio; // Hogyan fejlődik a játékos vagy a történet

    // --- Művészeti és Hangirány ---
    private String muveszetiStilus;
    private String hangirany; // Zene és hangeffektek stílusa

    // --- Monetizáció és Üzleti Modell ---
    private String monetizaciosStrategia; // Hogyan generál bevételt a játék (pl. prémium, F2P, DLC)

    /**
     * Konstruktor a GameDesignDocument objektum inicializálásához.
     *
     * @param cim A játék címe.
     * @param mufaj A játék műfaja (pl. RPG, FPS, stratégia).
     * @param platformok A támogatott platformok (pl. PC, PS5, Xbox, mobil).
     * @param celkozonseg A játék célközönsége.
     * @param logline Egy rövid, egy mondatos összefoglaló a játékról.
     * @param egyediEladasiPontok Mi teszi a játékot egyedivé.
     * @param jatekmenetHurok A játék alapvető játékmenet ciklusa.
     * @param sztoriSzintezis A játék történetének összefoglalása.
     * @param foKarakterek A főbb karakterek listája.
     * @param vilagEpites A játék világának leírása.
     * @param alapMechanikak A játék fő mechanikái.
     * @param iranyitas Az irányítás leírása.
     * @param progresszio A játékos/történet fejlődésének módja.
     * @param muveszetiStilus A játék művészeti stílusa.
     * @param hangirany A zenei és hangeffekt irányvonal.
     * @param monetizaciosStrategia A játék monetizációs stratégiája.
     */
    public GameDesignDocument(String cim, String mufaj, String platformok, String celkozonseg, String logline,
                              String egyediEladasiPontok, String jatekmenetHurok, String sztoriSzintezis,
                              List<String> foKarakterek, String vilagEpites, List<String> alapMechanikak,
                              String iranyitas, String progresszio, String muveszetiStilus,
                              String hangirany, String monetizaciosStrategia) {
        this.cim = cim;
        this.mufaj = mufaj;
        this.platformok = platformok;
        this.celkozonseg = celkozonseg;
        this.logline = logline;
        this.egyediEladasiPontok = egyediEladasiPontok;
        this.jatekmenetHurok = jatekmenetHurok;
        this.sztoriSzintezis = sztoriSzintezis;
        this.foKarakterek = foKarakterek;
        this.vilagEpites = vilagEpites;
        this.alapMechanikak = alapMechanikak;
        this.iranyitas = iranyitas;
        this.progresszio = progresszio;
        this.muveszetiStilus = muveszetiStilus;
        this.hangirany = hangirany;
        this.monetizaciosStrategia = monetizaciosStrategia;
    }

    /**
     * Kiírja a játéktervezési dokumentum összefoglalóját a konzolra.
     * Ez a metódus szimulálja a dokumentum "elkészítését" egy strukturált kimenet formájában.
     */
    public void dokumentumOsszefoglaloKiirasa() {
        System.out.println("--- JÁTÉKTERVEZÉSI DOKUMENTUM: " + cim.toUpperCase() + " ---");
        System.out.println("\n== 1. ÁLTALÁNOS INFORMÁCIÓK ==");
        System.out.println("Cím: " + cim);
        System.out.println("Műfaj: " + mufaj);
        System.out.println("Platformok: " + platformok);
        System.out.println("Célközönség: " + celkozonseg);
        System.out.println("Logline: " + logline);

        System.out.println("\n== 2. ALAPKONCEPCIÓ ==");
        System.out.println("Egyedi Eladási Pontok: " + egyediEladasiPontok);
        System.out.println("Játékmenet Hurok: " + jatekmenetHurok);

        System.out.println("\n== 3. NARRATÍVA ÉS VILÁG ==");
        System.out.println("Sztori Szintézis: " + sztoriSzintezis);
        System.out.println("Fő Karakterek: " + String.join(", ", foKarakterek));
        System.out.println("Világ Építés: " + vilagEpites);

        System.out.println("\n== 4. JÁTÉKMECHANIKA ==");
        System.out.println("Alap Mechanikák: " + String.join(", ", alapMechanikak));
        System.out.println("Irányítás: " + iranyitas);
        System.out.println("Progresszió: " + progresszio);

        System.out.println("\n== 5. MŰVÉSZETI ÉS HANGIRÁNY ==");
        System.out.println("Művészeti Stílus: " + muveszetiStilus);
        System.out.println("Hangirány: " + hangirany);

        System.out.println("\n== 6. MONETIZÁCIÓ ÉS ÜZLETI MODELL ==");
        System.out.println("Monetizációs Stratégia: " + monetizaciosStrategia);

        System.out.println("\n--- DOKUMENTUM VÉGE ---");
    }

    // Főbb Get metódusok, ha külső rendszereknek szüksége lenne az adatokra
    public String getCim() { return cim; }
    public String getMufaj() { return mufaj; }
    // ... további getterek ...

    /**
     * Fő metódus a GameDesignDocument osztály demonstrálására.
     * Létrehoz egy példányt és kiírja az összefoglalóját.
     */
    public static void main(String[] args) {
        List<String> karakterek = new ArrayList<>();
        karakterek.add("Elara, az Árnyékvadász");
        karakterek.add("Kael, az Ősi Varázsló");
        karakterek.add("Grok, a Barbár");

        List<String> mechanikak = new ArrayList<>();
        mechanikak.add("Valós idejű harc");
        mechanikak.add("Tárgykészítés és fejlesztés");
        mechanikak.add("Frakció alapú reputációrendszer");
        mechanikak.add("Dungeon felfedezés");

        GameDesignDocument fantasyRPG = new GameDesignDocument(
            "Eldoria Árnyai",
            "Fantasy Akció-RPG",
            "PC, PlayStation 5, Xbox Series X",
            "RPG rajongók, történetközpontú játékosok (16+)",
            "Egy árnyékból született hősnek kell megállítania az ősi gonoszt, mielőtt az elnyeli Eldoria utolsó fényét.",
            "Dinamikus, képességalapú harcrendszer, elágazó történet, procedurálisan generált dungeonok és crafting.",
            "Küldetés felvétele -> Felfedezés/Harc -> Loot/Karakterfejlesztés -> Új küldetés.",
            "Eldoria békés birodalmát egy ősi átok fenyegeti, mely sötétségbe taszítja a földet. Elara, egy 'Árnyékban Született' harcos, az egyetlen, aki képes ellenállni az árnyékoknak, és egy prófécia szerint ő a kiválasztott, aki visszaállíthatja a fényt.",
            karakterek,
            "Sötét fantasy világ, ahol a mágia és a régi civilizációk romjai keverednek a fenyegető árnyékkal.",
            mechanikak,
            "Egér + billentyűzet (PC), Gamepad (konzolok) - testreszabható gombkiosztással.",
            "Képességfa alapú fejlődés, ritka tárgyak gyűjtése, reputáció növelése frakcióknál, sztori alapú fejezetek.",
            "Sötét, realisztikus fantasy stílus, részletgazdag környezetek és karaktermodellek.",
            "Epikus, nagyzenekari filmzene, atmoszférikus hangeffektek, professzionális szinkronszínészek.",
            "Teljes árú prémium játék, opcionális kozmetikai DLC-k és egy jövőbeli kiegészítő (expansion pack)."
        );

        fantasyRPG.dokumentumOsszefoglaloKiirasa();
    }
}
