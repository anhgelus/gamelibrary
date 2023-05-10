package world.anhgelus.gamelibrary.team;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.util.config.Config;
import world.anhgelus.gamelibrary.util.serializer.TeamSerializer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamManager {
    private final static List<Team> TEAMS = new ArrayList<>();
    public static Config CONFIG;
    public static TeamProperties TYPE;

    /**
     * Save a team in the config file
     * @param team Team to save
     */
    public static void saveTeam(Team team) {
        if (CONFIG == null) throw new NullPointerException("Config is null or was not set");
        TeamSerializer.teamToConfig(team, CONFIG);
    }

    /**
     * Load every team from the config file
     * @param registerIt If true, register the teams in the TEAMS list
     * @return List of teams loaded from the config file
     */
    @Nullable
    public static List<Team> loadTeams(boolean registerIt) {
        final FileConfiguration config = CONFIG.get();
        final ConfigurationSection section = config.getConfigurationSection("teams");
        if (section == null) return null;
        final List<Team> teams = new ArrayList<>();
        section.getKeys(false).forEach(uuid -> {
            final Team team = TeamSerializer.teamFromConfig(CONFIG, UUID.fromString(uuid));
            if (team == null) return;
            if (registerIt) TEAMS.add(team);
            teams.add(team);
        });
        return teams;
    }

    /**
     * Register a team
     * @param team Team to register
     */
    public static void registerTeam(Team team) {
        TEAMS.add(team);
    }

    /**
     * Unregister a team
     * @param team Team to unregister
     */
    public static void unregisterTeam(Team team) {
        TEAMS.remove(team);
    }

    /**
     * Check if a team is registered
     * @param team Team to check
     * @return True if the team is registered, false otherwise
     */
    public static boolean isRegistered(Team team) {
        return TEAMS.contains(team);
    }

    /**
     * Check if a player has a team
     * @param player Player to check
     * @return True if the player has a team, false otherwise
     */
    public static boolean hasTeam(Player player) {
        for (Team team : TEAMS) {
            if (team.hasPlayer(player)) return true;
        }
        return false;
    }

    /**
     * Get a team by its name
     * @param name Name of the team
     * @return The team with the given name or null if no team with the given name exists
     */
    @Nullable
    public static Team getTeam(String name) {
        for (Team team : TEAMS) {
            if (team.getName().equalsIgnoreCase(name)) return team;
        }
        return null;
    }

    /**
     * Get a team by its UUID
     * @param uuid UUID of the team
     * @return The team with the given UUID or null if no team with the given UUID exists
     */
    @Nullable
    public static Team getTeam(UUID uuid) {
        for (Team team : TEAMS) {
            if (team.getUUID().equals(uuid)) return team;
        }
        return null;
    }

    /**
     * Get the team of a player
     * @param player Player to get the team of
     * @return The team with the given player or null if no team with the given player exists
     */
    @Nullable
    public static Team getTeam(Player player) {
        for (Team team : TEAMS) {
            if (team.hasPlayer(player)) return team;
        }
        return null;
    }

    /**
     * Get teams
     * @return Teams
     */
    @Nullable
    public static List<Team> getTeams() {
        if (TEAMS.isEmpty()) return null;
        return TEAMS;
    }
}
