package world.anhgelus.gamelibrary.game.engine;

public enum GameState {
    STARTING("Starting"),
    RUNNING("Running"),
    PAUSED("Paused"),
    ENDING("Ending"),
    NOT_STARTED("Not Started");

    final String name;

    GameState(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name;
    }
}
