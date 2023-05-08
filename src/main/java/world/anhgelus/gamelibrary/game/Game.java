package world.anhgelus.gamelibrary.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import world.anhgelus.gamelibrary.GameLibrary;
import world.anhgelus.gamelibrary.game.commands.GameCommandManager;
import world.anhgelus.gamelibrary.game.engine.GameEngine;
import world.anhgelus.gamelibrary.game.event.GameEventHandler;
import world.anhgelus.gamelibrary.game.event.events.AddPointGameEvent;
import world.anhgelus.gamelibrary.game.event.events.RemovePointGameEvent;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.util.SenderHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Plugin main;
    private final String name;
    private final GameEngine engine;
    private final GameProperties properties;
    private final GameCommandManager gameCommandManager;
    private final List<GameEventHandler> handlers = new ArrayList<>();

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
     * Register a game event handler
     * @param handler Handler to register
     */
    public void registerEventHandler(GameEventHandler handler) {
        handlers.add(handler);
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

    /**
     * Add points to a team
     * @param team Team to add points
     * @param points Points to add
     * @param player Player who add points (can be null)
     */
    public void addPointsToTeam(Team team, int points, @Nullable Player player) {
        main.getLogger().info("A point has been added to team " + team.getName() + ".");
        team.addPoints(points);
        final var event = new AddPointGameEvent(team, player, points);
        handlers.forEach(handler -> handler.onAddPointGameEvent(event));
        SenderHelper.broadcastInfo(MessageManager.parseMessage(GameLibrary.getGameMessages().getMessage("point_to_team"),
                player,this, team));
    }

    /**
     * Remove points to a team
     * @param team Team to remove points
     * @param points Points to remove
     * @param player Player who remove points (can be null)
     */
    public void removePointsToTeam(Team team, int points, @Nullable Player player) {
        main.getLogger().info("A point has been removed to team " + team.getName() + ".");
        team.addPoints(points);
        final var event = new RemovePointGameEvent(team, player, points);
        handlers.forEach(handler -> handler.onRemovePointGameEvent(event));
        SenderHelper.broadcastInfo(MessageManager.parseMessage(GameLibrary.getGameMessages().getMessage("remove_point_to_team"),
                player, this, team));
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
