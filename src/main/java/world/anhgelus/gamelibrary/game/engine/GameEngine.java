package world.anhgelus.gamelibrary.game.engine;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.messages.Message;

@Deprecated
public class GameEngine {
    private final Game game;
    private Message message;

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
    public void start(Player player) throws NullPointerException {}

    /**
     * End the game
     */
    public void end(Player player) {}

    /**
     * Pause the game
     */
    public void pause(Player player) {}

    /**
     * Resume the game
     */
    public void resume(Player player) {}

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
