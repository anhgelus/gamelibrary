package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamCommands;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class ListSubCmd extends Subcommand {
    public ListSubCmd() {
        super("list","List all teams",null);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        final List<Team> teams = TeamManager.getTeams();
        if (teams == null || teams.isEmpty()) {
            SenderHelper.sendError(player, "No teams found");
            return true;
        }
        TeamCommands.sendList(player, teams);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        return null;
    }
}
