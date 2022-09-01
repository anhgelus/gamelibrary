package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;
import java.util.stream.Collectors;

public class JoinSubCmd extends Subcommand {
    public JoinSubCmd() {
        super("join","Join a team",null);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            SenderHelper.sendWarning(player, "Usage: /team join <team> [player]");
            return true;
        }

        final String teamName = args[1];

        final Team team = TeamManager.getTeam(teamName);
        if (team == null) {
            SenderHelper.sendError(player, "Team not found: " + teamName);
            return true;
        }

        if (args.length > 2) {
            final String playerName = args[2];
            final Player targetPlayer = Bukkit.getPlayer(playerName);
            if (targetPlayer == null) {
                SenderHelper.sendError(player, "Player not found: " + playerName);
                return true;
            }
            team.addPlayer(targetPlayer);
            SenderHelper.sendSuccess(player, playerName +" joined the team " + teamName);
            SenderHelper.sendInfo(targetPlayer, "You joined the team " + teamName);
            return true;
        }

        team.addPlayer(player);
        
        SenderHelper.sendSuccess(player, "You joined the team " + teamName);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        if (args.length == 2) {
            return teamListName();
        }
        if (args.length == 3) {
            return playerListName();
        }
        return null;
    }
}
