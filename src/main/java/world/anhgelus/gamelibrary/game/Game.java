package world.anhgelus.gamelibrary.game;

import org.bukkit.plugin.Plugin;
import world.anhgelus.gamelibrary.game.engine.GameEngine;

public class Game {
    private final Plugin main;
    private final String name;
    private final GameEngine engine;

    /**
     *
     * @param main Main plugin
     * @param name Name of the game
     */
    public Game(Plugin main, String name) {
        this.main = main;
        this.name = name;
        this.engine = new GameEngine(this);
    }

    /**
     * Start the game
     */
    public void start() {
        main.getLogger().info("Game " + name + " has been started!");
        engine.start();
    }

    /**
     * Pause the game
     */
    public void pause() {
        main.getLogger().info("Game " + name + " has been paused!");
        engine.pause();
    }

    /**
     * Resume the game
     */
    public void resume() {
        main.getLogger().info("Game " + name + " has been resumed!");
        engine.resume();
    }

    /**
     * End the game
     */
    public void stop() {
        main.getLogger().info("Game " + name + " has been stopped!");
        engine.end();
    }

    public String getName() {
        return name;
    }

    public GameEngine getEngine() {
        return engine;
    }
}
