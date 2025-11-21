public class DevelopmentEnvironmentSetup {

    public static void main(String[] args) {
        System.out.println("--- Fejlesztői környezet és alap projektstruktúra beállítása ---");

        System.out.println("\n1. JDK (Java Development Kit) telepítése és konfigurálása.");
        System.out.println("   - Ellenőrzés: java -version");

        System.out.println("\n2. IDE (Integrált Fejlesztői Környezet) telepítése.");
        System.out.println("   - Például: IntelliJ IDEA, Eclipse, VS Code Java Extension Pack.");

        System.out.println("\n3. Build eszköz (Maven vagy Gradle) integrálása az IDE-be.");
        System.out.println("   - Maven: pom.xml, Gradle: build.gradle");

        System.out.println("\n4. Új Java projekt létrehozása az IDE-ben (pl. Maven/Gradle sablonnal).");
        System.out.println("   - Projekt neve: MyFirstJavaProject");
        System.out.println("   - Csoport azonosító: com.example");
        System.out.println("   - Artefakt azonosító: my-first-app");

        System.out.println("\n5. Alapvető projektstruktúra ellenőrzése:");
        System.out.println("   - my-first-app/");
        System.out.println("     - src/");
        System.out.println("       - main/");
        System.out.println("         - java/");
        System.out.println("           - com/example/myfirstapp/");
        System.out.println("             - MainApplication.java (vagy hasonló)");
        System.out.println("       - test/");
        System.out.println("         - java/");
        System.out.println("     - pom.xml (Maven) / build.gradle (Gradle)");

        System.out.println("\n6. Első Java osztály létrehozása és futtatása (pl. MainApplication.java):");
        System.out.println("   