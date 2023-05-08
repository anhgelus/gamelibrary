package world.anhgelus.gamelibrary.chronometer;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import world.anhgelus.gamelibrary.GameLibrary;

import java.util.logging.Logger;

public class Chronometer {
    private final long time;
    private final long startTime;
    private long timeLeft;

    private boolean isEndless = false;
    private boolean isAsync;

    private final Runnable onEnd;
    private BukkitTask task;

    private static final Logger LOGGER = GameLibrary.getLOGGER();

    /**
     * Create a new Chronometer
     *
     * @param time  Time in seconds
     * @param onEnd Runnable to execute when the time is over
     */
    public Chronometer(long time, Runnable onEnd) {
        this.time = time;
        this.onEnd = onEnd;
        this.startTime = 0;
    }

    /**
     * Create a new Chronometer
     *
     * @param time      Time in seconds
     * @param startTime Time in seconds when the chronometer started
     * @param onEnd    Runnable to execute when the time is over
     */
    public Chronometer(long time, Runnable onEnd, long startTime) {
        this.time = time;
        this.startTime = startTime;
        this.onEnd = onEnd;
    }

    /**
     * Create a new Chronometer
     *
     * @param time      Time in seconds
     * @param startTime Time in seconds when the chronometer started
     * @param isEndless True if the chronometer is endless, false otherwise
     * @param onEnd    Runnable to execute when the time is over
     */
    public Chronometer(long time, Runnable onEnd, long startTime, boolean isEndless) {
        this.time = time;
        this.startTime = startTime;
        this.isEndless = isEndless;
        this.onEnd = onEnd;
    }

    /**
     * Create a new Chronometer
     *
     * @param time      Time in seconds
     * @param isEndless True if the chronometer is endless, false otherwise
     * @param onEnd    Runnable to execute when the time is over
     */
    public Chronometer(long time,Runnable onEnd, boolean isEndless) {
        this.time = time;
        this.onEnd = onEnd;
        this.startTime = 0;
        this.isEndless = isEndless;
    }

    /**
     * Start the chronometer
     * @param isAsync True if the chronometer should be started asynchronously, false otherwise
     */
    public void start(boolean isAsync) {
        timeLeft = time - startTime;
        this.isAsync = isAsync;
        runTask();
        LOGGER.info("Chronometer with id "+task.getTaskId()+" started");
    }

    /**
     * Pause the chronometer
     */
    public void pause() {
        if (task != null) {
            LOGGER.info("Chronometer with id "+task.getTaskId()+" paused at "+(time - timeLeft));
            task.cancel();
        }
    }

    /**
     * Resume the chronometer
     */
    public void resume() {
        runTask();
        LOGGER.info("Chronometer with id "+task.getTaskId()+" resumed at "+(time - timeLeft));
    }

    /**
     * Stop the chronometer
     */
    public void stop() {
        if (task != null) {
            task.cancel();
        }
        onEnd.run();
        LOGGER.info("Chronometer with id "+task.getTaskId()+" stopped at "+(time - timeLeft));
    }

    /**
     * Run the task
     */
    private void runTask() {
        if (isAsync) {
            task = Bukkit.getScheduler().runTaskTimerAsynchronously(GameLibrary.getInstance(), () -> {
                timeLeft--;
                if (!isEndless && timeLeft <= 0) {
                    onEnd.run();
                    task.cancel();
                }
            }, 0, 20);
            return;
        }
        task = Bukkit.getScheduler().runTaskTimer(GameLibrary.getInstance(), () -> {
            timeLeft--;
            if (!isEndless && timeLeft <= 0) {
                onEnd.run();
                task.cancel();
            }
        }, 0, 20);
    }
}
