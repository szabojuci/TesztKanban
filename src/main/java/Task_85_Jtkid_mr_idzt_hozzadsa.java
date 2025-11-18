public class GameTimer {
    private long startTimeNanos;
    private long elapsedTimeNanos;
    private boolean isRunning;

    public GameTimer() {
        reset();
    }

    public void start() {
        if (!isRunning) {
            startTimeNanos = System.nanoTime();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTimeNanos += (System.nanoTime() - startTimeNanos);
            isRunning = false;
        }
    }

    public void reset() {
        startTimeNanos = 0;
        elapsedTimeNanos = 0;
        isRunning = false;
    }

    /**
     * Returns the total elapsed time in milliseconds.
     * If the timer is running, it includes the current segment.
     *
     * @return Elapsed time in milliseconds.
     */
    public long getElapsedTimeMillis() {
        if (isRunning) {
            return (elapsedTimeNanos + (System.nanoTime() - startTimeNanos)) / 1_000_000;
        } else {
            return elapsedTimeNanos / 1_000_000;
        }
    }

    /**
     * Returns the total elapsed time in seconds.
     * If the timer is running, it includes the current segment.
     *
     * @return Elapsed time in seconds.
     */
    public double getElapsedTimeSeconds() {
        return getElapsedTimeMillis() / 1000.0;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
