package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class LeaveSubCmd extends Subcommand {
    public LeaveSubCmd() {
        super("leave", "Leave your current team", new Permission("gamelibrary.team.leave"));
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 1) {
            SenderHelper.sendWarning(player, "Usage: /team leave [player]");
            return true;
        }
        if (args.length > 1) {
            final String playerName = args[1];
            final Player targetPlayer = Bukkit.getPlayer(playerName);
            if (targetPlayer == null) {
                SenderHelper.sendError(player, "Player not found: " + playerName);
                return true;
            }
            if (!TeamManager.hasTeam(targetPlayer)) {
                SenderHelper.sendError(player, playerName +" is not in a team");
                return true;
            }
            final Team team = TeamManager.getTeam(targetPlayer);
            if (team == null) {
                SenderHelper.sendError(player, "Team of the player not found");
                return true;
            }
            team.removePlayer(player);
            SenderHelper.sendSuccess(player, playerName +" left the team " + team.getName());
            return true;
        }
        if (!TeamManager.hasTeam(player)) {
            SenderHelper.sendError(player, "You are not in a team");
            return true;
        }
        final Team team = TeamManager.getTeam(player);
        if (team == null) {
            SenderHelper.sendError(player, "Team of the player not found");
            return true;
        }
        team.removePlayer(player);
        SenderHelper.sendSuccess(player, "You left the team " + team.getName());
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        if (args.length == 2) {
            return playerListName();
        }
        if (args.length == 3) {
            return teamListName();
        }
        return null;
    }
}
