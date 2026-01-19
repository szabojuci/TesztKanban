public class GameEngine {
    private volatile boolean running = false;
    private final double TARGET_UPS = 60.0;
    private final double NANO_PER_UPDATE = 1_000_000_000.0 / TARGET_UPS;

    public void startGame() {
        running = true;
        gameLoop();
    }

    public void stopGame() {
        running = false;
    }

    private void gameLoop() {
        long lastLoopTime = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            long timeElapsedSinceLastLoop = now - lastLoopTime;
            lastLoopTime = now;

            delta += timeElapsedSinceLastLoop / NANO_PER_UPDATE;

            while (delta >= 1) {
                update();
                delta--;
            }

            render();

            long timeTakenThisFrame = System.nanoTime() - now;
            long sleepTimeNs = (long) (NANO_PER_UPDATE - timeTakenThisFrame);

            if (sleepTimeNs > 0) {
                try {
                    Thread.sleep(sleepTimeNs / 1_000_000, (int) (sleepTimeNs % 1_000_000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false;
                }
            }
        }
    }

    private void update() {
        // Implement game logic updates here
    }

    private void render() {
        // Implement game rendering here
    }
}
