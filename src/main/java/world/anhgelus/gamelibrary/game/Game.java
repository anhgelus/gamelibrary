package world.anhgelus.gamelibrary.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import world.anhgelus.gamelibrary.GameLibrary;
import world.anhgelus.gamelibrary.game.commands.GameCommandManager;
import world.anhgelus.gamelibrary.game.engine.GameEngine;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class Game {
    private final Plugin main;
    private final String name;
    private final GameEngine engine;
    private final GameProperties properties;
    private final GameCommandManager gameCommandManager;

    /**
     *
     * @param main Main plugin
     * @param name Name of the game
     */
    public Game(Plugin main, String name) {
        this.main = main;
        this.name = name;
        this.engine = new GameEngine(this, GameLibrary.getGameMessages());
        this.properties = new GameProperties(name);
        this.gameCommandManager = new GameCommandManager(this, properties);
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

    public void addPointsToTeam(Team team, int points) {
        main.getLogger().info("A point has been added to team " + team.getName() + ".");
        team.addPoints(points);
        SenderHelper.broadcastInfo(MessageManager.parseMessage(GameLibrary.getGameMessages().getMessage("point_to_team"),
                this, team));
    }

    public void removePointsToTeam(Team team, int points) {
        main.getLogger().info("A point has been removed to team " + team.getName() + ".");
        team.addPoints(points);
        SenderHelper.broadcastInfo(MessageManager.parseMessage(GameLibrary.getGameMessages().getMessage("remove_point_to_team"),
                this, team));
    }

    public String getName() {
        return name;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public GameProperties getProperties() {
        return properties;
    }

    public GameCommandManager getCommandManager() {
        return gameCommandManager;
    }
}
