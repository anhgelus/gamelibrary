package world.anhgelus.gamelibrary.event.generic;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.team.Team;

import java.util.List;

public interface GenericStateGameEvent {
    List<Team> getPlayingTeams();
    List<Player> getPlayingPlayers();
    Game getGame();
}
