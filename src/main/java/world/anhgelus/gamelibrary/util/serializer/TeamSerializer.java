package world.anhgelus.gamelibrary.util.serializer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.util.config.Config;

import java.util.List;
import java.util.UUID;

public class TeamSerializer {
    /**
     * Save a team in a config
     * @param team Team to save
     * @param config Config to save the team in
     */
    public static void teamToConfig(Team team, Config config) {
        final FileConfiguration section = config.get();
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
     * @param uuid UUID of the team to load
     * @param propertiesClass Properties class for teams
     * @return Team loaded from the config
     */
    public static Team teamFromConfig(Config config, UUID uuid, String propertiesClass) {
        final FileConfiguration section = config.get();
        final String key = "teams" + "." + uuid.toString() + ".";
        final String name = section.getString(key + "name");
        final String prefix = section.getString(key + "prefix");
        final ChatColor color = ChatColor.valueOf(section.getString(key + "color"));
        final String unformattedPlayers = section.getString(key + "players");
        if (unformattedPlayers == null) return null;
        final List<Player> players = PlayerSerializer.stringToPlayers(unformattedPlayers);
        final Team team = new Team(name, prefix, color, propertiesClass);
        team.addPlayers(players);
        return team;
    }
}
