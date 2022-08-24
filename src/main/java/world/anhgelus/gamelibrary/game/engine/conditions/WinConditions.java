package world.anhgelus.gamelibrary.game.engine.conditions;

import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.engine.GameState;

public interface WinConditions {
    /**
     * Executed to check when the game is ended
     * @return true if the game is ended
     */
    public boolean isFinished(Game game);

    /**
     * Executed when the game is finished
     */
    public void onWin(Game game);
}
