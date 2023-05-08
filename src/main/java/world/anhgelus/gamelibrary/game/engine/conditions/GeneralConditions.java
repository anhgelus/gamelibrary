package world.anhgelus.gamelibrary.game.engine.conditions;

import world.anhgelus.gamelibrary.game.Game;

@Deprecated
public interface GeneralConditions {
    /**
     * Executed when the game is paused
     * @param game Game
     */
    void onPause(Game game);

    /**
     * Executed when the game is resumed
     * @param game Game
     */
    void onResume(Game game);
}
