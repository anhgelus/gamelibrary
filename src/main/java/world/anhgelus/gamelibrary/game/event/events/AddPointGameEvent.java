package world.anhgelus.gamelibrary.game.event.events;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.event.GameEvent;
import world.anhgelus.gamelibrary.game.event.generic.GenericPointGameEvent;
import world.anhgelus.gamelibrary.team.Team;

import javax.annotation.Nullable;

/**
 * Fired when a team got a point
 */
public class AddPointGameEvent extends GameEvent implements GenericPointGameEvent {
    public final Team team;
    /**
     * Can be null
     */
    public final Player player;
    public final int pointsAdded;

    public AddPointGameEvent(Team team, @Nullable Player player, int pointsAdded) {
        this.team = team;
        this.player = player;
        this.pointsAdded = pointsAdded;
    }

    @Override
    public int pointsBefore() {
        return team.getPoints()-pointsAdded;
    }

    @Override
    public void cancel() throws UnsupportedOperationException {
        team.removePoints(pointsAdded);
    }
}
