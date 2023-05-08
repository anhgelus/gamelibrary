package world.anhgelus.gamelibrary.game.engine.conditions;

import world.anhgelus.gamelibrary.game.Game;

@Deprecated
public interface StartConditions {
    /**
     * Executed when the game is started
     * @return True if the game can start, false otherwise
     */
    boolean onStart(Game game);
}
