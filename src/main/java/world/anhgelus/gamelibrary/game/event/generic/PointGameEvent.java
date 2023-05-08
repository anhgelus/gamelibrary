package world.anhgelus.gamelibrary.game.event.generic;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.event.GameEvent;
import world.anhgelus.gamelibrary.team.Team;

/**
 * Fired when a team got a point
 */
public class PointGameEvent extends GameEvent {
    public final Team team;
    public final Player player;
    public final int newPoints;

    public PointGameEvent(Team team, Player player, int newPoints) {
        this.team = team;
        this.player = player;
        this.newPoints = newPoints;
    }

    @Override
    public void cancel() throws UnsupportedOperationException {
        team.removePoints(1);
    }
}
