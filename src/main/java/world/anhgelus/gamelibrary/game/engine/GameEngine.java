package world.anhgelus.gamelibrary.game.engine;

import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.engine.conditions.GeneralConditions;
import world.anhgelus.gamelibrary.game.engine.conditions.StartConditions;
import world.anhgelus.gamelibrary.game.engine.conditions.WinConditions;

public class GameEngine {
    private final Game game;
    private WinConditions winConditions;
    private StartConditions startConditions;
    private GeneralConditions generalConditions;

    private GameState state = GameState.NOT_STARTED;

    /**
     *
     * @param game Game
     */
    public GameEngine(Game game) {
        this.game = game;
    }

    /**
     * Start the game
     * @throws NullPointerException if the conditions was not set
     */
    public void start() throws NullPointerException {
        if (startConditions == null || winConditions == null) {
            throw new NullPointerException("startConditions and winConditions cannot be null");
        }
        if (state != GameState.NOT_STARTED) {
            throw new IllegalStateException("Game has already started");
        }
        state = GameState.STARTING;
        startConditions.onStart(game);
    }

    /**
     * End the game
     */
    public void end() {
        if (state != GameState.RUNNING) {
            throw new IllegalStateException("Game was not started");
        }
        state = GameState.ENDING;
        winConditions.onWin(game);
    }

    /**
     * Pause the game
     */
    public void pause() {
        if (state != GameState.RUNNING) {
            throw new IllegalStateException("Game was not started");
        }
        state = GameState.PAUSED;
        generalConditions.onPause(game);
    }

    /**
     * Resume the game
     */
    public void resume() {
        if (state != GameState.PAUSED) {
            throw new IllegalStateException("Game was not paused");
        }
        state = GameState.RUNNING;
        generalConditions.onResume(game);
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
}
