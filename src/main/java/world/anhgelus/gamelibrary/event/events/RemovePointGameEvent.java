package world.anhgelus.gamelibrary.event.events;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.event.generic.GenericPointGameEvent;
import world.anhgelus.gamelibrary.event.GameEvent;
import world.anhgelus.gamelibrary.team.Team;

import javax.annotation.Nullable;

/**
 * Fired when a team got a point
 */
public class RemovePointGameEvent extends GameEvent implements GenericPointGameEvent {
    public final Team team;
    /**
     * Can be null
     */
    public final Player player;
    public final int pointsRemoved;

    public RemovePointGameEvent(Team team, @Nullable Player player, int pointsRemoved) {
        this.team = team;
        this.player = player;
        this.pointsRemoved = pointsRemoved;
    }

    @Override
    public int pointsBefore() {
        return team.getPoints()-pointsRemoved;
    }

    @Override
    public void cancel() throws UnsupportedOperationException {
        team.addPoints(pointsRemoved);
    }
}
