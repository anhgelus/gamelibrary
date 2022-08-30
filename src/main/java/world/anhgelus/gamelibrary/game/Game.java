package world.anhgelus.gamelibrary.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import world.anhgelus.gamelibrary.GameLibrary;
import world.anhgelus.gamelibrary.game.commands.GameCommandManager;
import world.anhgelus.gamelibrary.game.engine.GameEngine;

public class Game {
    private final Plugin main;
    private final String name;
    private final GameEngine engine;
    private final GameProperties properties;
    private final GameCommandManager gameCommandManager;

    private static Game INSTANCE;

    /**
     *
     * @param main Main plugin
     * @param name Name of the game
     */
    public Game(Plugin main, String name) {
        this.main = main;
        this.name = name;
        this.engine = new GameEngine(this, GameLibrary.getGameMessages());
        INSTANCE = this;
        this.properties = new GameProperties(name);
        this.gameCommandManager = new GameCommandManager(properties);
    }

    /**
     * Start the game
     */
    public void start(Player player) {
        main.getLogger().info("Game " + name + " has been started!");
        engine.start(player);
    }

    /**
     * Pause the game
     */
    public void pause(Player player) {
        main.getLogger().info("Game " + name + " has been paused!");
        engine.pause(player);
    }

    /**
     * Resume the game
     */
    public void resume(Player player) {
        main.getLogger().info("Game " + name + " has been resumed!");
        engine.resume(player);
    }

    /**
     * End the game
     */
    public void stop(Player player) {
        main.getLogger().info("Game " + name + " has been stopped!");
        engine.end(player);
    }

    public String getName() {
        return name;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public static Game getInstance() throws NullPointerException {
        if (INSTANCE == null) {
            throw new NullPointerException("Game is null!");
        }
        return INSTANCE;
    }

    public GameProperties getProperties() {
        return properties;
    }

    public GameCommandManager getCommandManager() {
        return gameCommandManager;
    }
}
