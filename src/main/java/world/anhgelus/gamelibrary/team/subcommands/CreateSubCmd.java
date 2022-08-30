package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class CreateSubCmd extends Subcommand {
    public CreateSubCmd() {
        super("create","Create a team",null);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 4) {
            SenderHelper.sendError(player, "Usage: /team create <teamName> <prefix> <color>");
            return true;
        }
        final String teamName = args[1];
        final String prefix = args[2];
        final ChatColor color = ChatColor.valueOf(args[3]);
        TeamManager.createTeam(teamName, prefix, color);
        SenderHelper.sendSuccess(player, "Team " + teamName + " created");
        return true;
    }
}
