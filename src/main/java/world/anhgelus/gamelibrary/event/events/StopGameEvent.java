package world.anhgelus.gamelibrary.event.events;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.event.GameEvent;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.event.generic.GenericStateGameEvent;
import world.anhgelus.gamelibrary.team.Team;

import java.util.List;

public class StopGameEvent extends GameEvent implements GenericStateGameEvent {
    public final List<Team> teams;
    public final List<Player> players;
    public final Game game;

    public StopGameEvent(Game game, List<Team> teams, List<Player> players) {
        this.teams = teams;
        this.players = players;
        this.game = game;
    }

    @Override
    public void cancel() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot cancel a start game event");
    }

    @Override
    public List<Team> getPlayingTeams() {
        return teams;
    }

    @Override
    public List<Player> getPlayingPlayers() {
        return players;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
