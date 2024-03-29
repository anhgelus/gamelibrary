package world.anhgelus.gamelibrary.event;

public abstract class GameEvent {
    /**
     * Cancel the event
     * @throws UnsupportedOperationException if the event cannot be cancelled
     */
    public abstract void cancel() throws UnsupportedOperationException;
}
