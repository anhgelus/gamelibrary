package world.anhgelus.gamelibrary.game.engine.conditions;

import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.engine.GameState;

public interface WinConditions {
    /**
     * Executed when the game is finished
     */
    public void onWin(Game game);
}
