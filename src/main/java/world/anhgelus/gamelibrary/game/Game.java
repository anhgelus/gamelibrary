package world.anhgelus.gamelibrary.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import world.anhgelus.gamelibrary.GameLibrary;
import world.anhgelus.gamelibrary.game.commands.GameCommandManager;
import world.anhgelus.gamelibrary.event.events.*;
import world.anhgelus.gamelibrary.event.*;
import world.anhgelus.gamelibrary.messages.Message;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Plugin main;
    private final String name;
    private final GameProperties properties;
    private final GameCommandManager gameCommandManager;
    private final List<GameEventHandler> handlers = new ArrayList<>();
    private final Message message = GameLibrary.getGameMessages();

    private GameState state = GameState.NOT_STARTED;

    /**
     *
     * @param main Main plugin
     * @param name Name of the game
     */
    public Game(Plugin main, String name) {
        this.main = main;
        this.name = name;
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
        main.getLogger().info("Game " + name + " has been started by "+player.getName()+" !");
        if (state != GameState.NOT_STARTED) {
            SenderHelper.sendError(player, "The game is not not started.");
        }
        state = GameState.STARTING;

        final var playing = getPlaying(player);
        if (playing == null) {
            return;
        }
        final var event = new StartGameEvent(this, playing.teams, playing.players);
        handlers.forEach(handler -> handler.onStart(event));
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("start"), player, this));
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("start_creator"),
                player, this));
    }

    /**
     * Pause the game
     */
    public void pause(Player player) {
        main.getLogger().info("Game " + name + " has been paused!");
        if (state != GameState.RUNNING) {
            SenderHelper.sendError(player, "The game is not running.");
        }
        state = GameState.PAUSED;
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("pause"), player, this));

        final var playing = getPlaying(player);
        if (playing == null) {
            return;
        }

        final var event = new PauseGameEvent(this, playing.teams, playing.players);
        handlers.forEach(handler -> handler.onPause(event));
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("pause_creator"),
                player, this));
    }

    /**
     * Resume the game
     */
    public void resume(Player player) {
        main.getLogger().info("Game " + name + " has been resumed!");
        if (state != GameState.PAUSED) {
            SenderHelper.sendError(player, "The game is not paused.");
        }
        state = GameState.RUNNING;

        final var playing = getPlaying(player);
        if (playing == null) {
            return;
        }

        final var event = new ResumeGameEvent(this, playing.teams, playing.players);
        handlers.forEach(handler -> handler.onResume(event));

        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("resume"), player, this));
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("resume_creator"),
                player, this));
    }

    /**
     * End the game
     */
    public void stop(Player player) {
        main.getLogger().info("Game " + name + " has been stopped!");
        if (state != GameState.RUNNING) {
            SenderHelper.sendError(player, "The game is not running.");
        }
        state = GameState.ENDING;

        final var playing = getPlaying(player);
        if (playing == null) {
            return;
        }

        final var event = new StopGameEvent(this, playing.teams, playing.players);
        handlers.forEach(handler -> handler.onStop(event));

        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("end"), player, this));
        state = GameState.NOT_STARTED;
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("end_creator"),
                player, this));
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
        handlers.forEach(handler -> handler.onAddPoint(event));
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
        handlers.forEach(handler -> handler.onRemovePoint(event));
        SenderHelper.broadcastInfo(MessageManager.parseMessage(GameLibrary.getGameMessages().getMessage("remove_point_to_team"),
                player, this, team));
    }

    public String getName() {
        return name;
    }


    public GameProperties getProperties() {
        return properties;
    }

    public GameCommandManager getCommandManager() {
        return gameCommandManager;
    }

    public GameState getState() {
        return state;
    }

    private record Playing(List<Team> teams, List<Player> players) {}

    @Nullable
    private Playing getPlaying(Player player) {
        final var teams = TeamManager.getTeams();
        if (teams == null) {
            SenderHelper.sendError(player, "Not team found.");
            return null;
        }
        if (teams.size() < 2) {
            SenderHelper.sendError(player, "There is not enough teams to start the game.");
            return null;
        }
        final var players = new ArrayList<Player>();
        teams.forEach(team -> players.addAll(team.getPlayers()));
        return new Playing(teams, players);
    }
}
