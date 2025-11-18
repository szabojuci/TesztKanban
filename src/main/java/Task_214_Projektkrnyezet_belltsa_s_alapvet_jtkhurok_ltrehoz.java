import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Egy egyszerű játékmotor, amely beállítja a projekt környezetét (ablak) és létrehoz egy alapvető játékhurkot.
 * A Canvas-t használja a rajzoláshoz a BufferStrategy-vel a simább grafika érdekében.
 */
public class Game extends Canvas implements Runnable {

    // Ablak és játék beállítások
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Basic Game Loop";
    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME_NANO = 1_000_000_000 / TARGET_FPS; // Nano másodperc per képkocka

    private boolean running = false;
    private Thread gameThread;

    // Egyszerű játékállapot: egy mozgó négyzet
    private int playerX = WIDTH / 2 - 25;
    private int playerY = HEIGHT / 2 - 25;
    private int playerSize = 50;
    private int playerSpeed = 2; // Pixel per frissítés

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true); // Lehetővé teszi a billentyűzet és egér események kezelését
        requestFocus(); // Kérés a fókuszra
    }

    /**
     * Elindítja a játékhurkot egy új szálon.
     */
    public synchronized void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this, "GameLoopThread");
        gameThread.start();
    }

    /**
     * Leállítja a játékhurkot és megvárja a szál befejezését.
     */
    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join(); // Megvárja a játékszál befejezését
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Game thread interrupted during stop: " + e.getMessage());
        }
    }

    /**
     * Frissíti a játék logikáját.
     */
    private void update() {
        // Egyszerű logika: a négyzet mozgatása balról jobbra
        playerX += playerSpeed;
        if (playerX + playerSize > WIDTH) {
            playerX = 0; // Vissza a bal oldalra
        }
    }

    /**
     * Megrajzolja a játék állapotát a képernyőre.
     */
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // Hármas pufferelés létrehozása a simább animációhoz
            return;
        }

        Graphics g = bs.getDrawGraphics();
        // Töröljük a teljes képernyőt
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Rajzoljuk a játék elemeit
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerSize, playerSize);

        g.dispose(); // Felszabadítja a grafikus erőforrásokat
        bs.show();   // Megjeleníti a következő puffert
    }

    /**
     * A játékhurok fő metódusa.
     */
    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        long lastFPSTime = 0;
        int fps = 0;

        while (running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            // Frissítjük a játék logikáját
            update();

            // Megjelenítjük a játékot
            render();

            // FPS számláló (opcionális, hibakereséshez hasznos)
            lastFPSTime += updateLength;
            fps++;
            if (lastFPSTime >= 1_000_000_000) { // Minden másodpercben kiírja az FPS-t
                System.out.println("FPS: " + fps);
                lastFPSTime = 0;
                fps = 0;
            }

            // Alvás a cél FPS fenntartásához
            try {
                // Kiszámolja, mennyi ideig kell aludni a következő képkockáig
                long sleepTimeMs = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME_NANO) / 1_000_000;
                if (sleepTimeMs > 0) {
                    Thread.sleep(sleepTimeMs);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Game loop interrupted: " + e.getMessage());
                running = false; // Leállítja a hurkot megszakítás esetén
            }
        }
    }

    /**
     * Fő metódus a játék indításához. Létrehozza az ablakot és elindítja a játékhurkot.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        Game game = new Game();
        frame.add(game); // Hozzáadja a Canvas-t az ablakhoz
        frame.pack();    // Az ablak méretét a komponensek preferált méretéhez igazítja

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Késleltetett bezárás
        frame.setLocationRelativeTo(null); // Középre helyezi az ablakot
        frame.setResizable(false); // Letiltja az ablak átméretezését
        frame.setVisible(true); // Megjeleníti az ablakot

        // Ablak bezárásának kezelése a játékhurok leállításához
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                game.stop(); // Leállítja a játékhurkot
                frame.dispose(); // Bezárja az ablakot
                System.exit(0); // Kilép a programból
            }
        });

        game.start(); // Elindítja a játékhurkot, miután az ablak láthatóvá vált
    }
}
