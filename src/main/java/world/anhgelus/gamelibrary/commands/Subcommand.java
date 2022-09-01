package world.anhgelus.gamelibrary.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Subcommand {
    /**
     * Identifier of the subcommand.
     * Example: hey for /game hey
     */
    private final String identifier;
    private final String description;
    protected final GameProperties properties;

    public Subcommand(String identifier, String description, GameProperties properties) {
        this.identifier = identifier;
        this.description = description;
        this.properties = properties;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    /**
     * When the subcommand is executed.
     * @param player Player who executed the subcommand.
     * @param args Arguments of the subcommand. The first args is the identifier of the subcommand.
     * @return True if the subcommand was executed successfully like spigot CommandsExecutor::onCommand.
     */
    public abstract boolean onCommand(Player player, String[] args);

    /**
     * Tab completer for the subcommand.
     * @param player Player who executed the subcommand.
     * @param args Arguments of the subcommand. The first args is the identifier of the subcommand.
     * @return List of possible completions.
     */
    public abstract List<String> getTabCompleter(Player player, String[] args);

    /**
     * Get the list of player's name
     * @return List of player's name
     */
    protected List<String> playerListName() {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }

    /**
     * Get the list of team's name
     * @return List of team's name
     */
    @Nullable
    protected List<String> teamListName() {
        final List<Team> teams = TeamManager.getTeams();
        if (teams == null || teams.isEmpty()) {
            return null;
        }
        return teams.stream().map(Team::getName).collect(Collectors.toList());
    }
}
