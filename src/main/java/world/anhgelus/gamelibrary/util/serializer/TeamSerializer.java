package world.anhgelus.gamelibrary.util.serializer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.util.config.Config;

import java.util.List;

public class TeamSerializer {
    /**
     * Save a team in a config
     * @param team Team to save
     * @param config Config to save the team in
     */
    public static void teamToConfig(Team team, Config config) {
        final ConfigurationSection section = config.get();
        final String key = "teams" + "." + team.getUUID().toString() + ".";
        section.set(key + "name", team.getName());
        section.set(key + "prefix", team.getPrefix());
        section.set(key + "color", team.getColor().name());
        section.set(key + "players", PlayerSerializer.playerToString(team.getPlayers()));
        section.set(key + "uuid", team.getUUID().toString());
        config.save();
    }

    /**
     * Load a team from a config
     * @param config Config to load the team from
     * @return Team loaded from the config
     */
    public static Team teamFromConfig(Config config) {
        final ConfigurationSection section = config.get();
        final String key = "teams" + "." + section.getString("uuid") + ".";
        final String name = section.getString(key + "name");
        final String prefix = section.getString(key + "prefix");
        final ChatColor color = ChatColor.valueOf(section.getString(key + "color"));
        final String unformattedPlayers = section.getString(key + "players");
        if (unformattedPlayers == null) return null;
        final List<Player> players = PlayerSerializer.stringToPlayers(unformattedPlayers);
        final Team team = new Team(name, prefix, color);
        team.addPlayers(players);
        return team;
    }
}
