package world.anhgelus.gamelibrary.game.engine;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.engine.conditions.GeneralConditions;
import world.anhgelus.gamelibrary.game.engine.conditions.StartConditions;
import world.anhgelus.gamelibrary.game.engine.conditions.WinConditions;
import world.anhgelus.gamelibrary.messages.Message;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class GameEngine {
    private final Game game;
    private Message message;

    private WinConditions winConditions;
    private StartConditions startConditions;
    private GeneralConditions generalConditions;

    private GameState state = GameState.NOT_STARTED;

    /**
     *
     * @param game Game
     */
    public GameEngine(Game game, Message message) {
        this.game = game;
        this.message = message;
    }

    /**
     * Start the game
     * @throws NullPointerException if the conditions was not set
     */
    public void start(Player player) throws NullPointerException {
        if (startConditions == null || winConditions == null) {
            throw new NullPointerException("startConditions and winConditions cannot be null");
        }
        if (state != GameState.NOT_STARTED) {
            SenderHelper.sendError(player, "The game is not not started.");
        }
        state = GameState.STARTING;
        startConditions.onStart(game);
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("start"), game.getName(), player.getName()));
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("start_creator"),
                game.getName(), player.getName()));
    }

    /**
     * End the game
     */
    public void end(Player player) {
        if (state != GameState.RUNNING) {
            SenderHelper.sendError(player, "The game is not running.");
        }
        state = GameState.ENDING;
        winConditions.onWin(game);
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("end"), game.getName(), player.getName()));
        state = GameState.NOT_STARTED;
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("end_creator"),
                game.getName(), player.getName()));
    }

    /**
     * Pause the game
     */
    public void pause(Player player) {
        if (state != GameState.RUNNING) {
            SenderHelper.sendError(player, "The game is not running.");
        }
        state = GameState.PAUSED;
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("pause"), game.getName(), player.getName()));
        generalConditions.onPause(game);
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("pause_creator"),
                game.getName(), player.getName()));
    }

    /**
     * Resume the game
     */
    public void resume(Player player) {
        if (state != GameState.PAUSED) {
            SenderHelper.sendError(player, "The game is not paused.");
        }
        state = GameState.RUNNING;
        generalConditions.onResume(game);
        SenderHelper.broadcastSuccess(MessageManager.parseMessage(message.getMessage("resume"), game.getName(), player.getName()));
        SenderHelper.sendSuccess(player, MessageManager.parseMessage(message.getMessage("resume_creator"),
                game.getName(), player.getName()));
    }

    public void setWinConditions(WinConditions winConditions) {
        this.winConditions = winConditions;
    }

    public StartConditions getStartConditions() {
        return startConditions;
    }

    public void setStartConditions(StartConditions startConditions) {
        this.startConditions = startConditions;
    }

    public WinConditions getWinConditions() {
        return winConditions;
    }

    public GeneralConditions getGeneralConditions() {
        return generalConditions;
    }

    public void setGeneralConditions(GeneralConditions generalConditions) {
        this.generalConditions = generalConditions;
    }

    public Game getGame() {
        return game;
    }

    public GameState getState() {
        return state;
    }

    public void setMessages(Message message) {
        this.message = message;
    }
}
