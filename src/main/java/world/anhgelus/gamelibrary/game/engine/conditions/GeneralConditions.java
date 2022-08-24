package world.anhgelus.gamelibrary.game.engine.conditions;

import world.anhgelus.gamelibrary.game.Game;

public interface GeneralConditions {
    /**
     * Executed when the game is paused
     * @param game Game
     */
    public void onPause(Game game);

    /**
     * Executed when the game is resumed
     * @param game Game
     */
    public void onResume(Game game);
}
