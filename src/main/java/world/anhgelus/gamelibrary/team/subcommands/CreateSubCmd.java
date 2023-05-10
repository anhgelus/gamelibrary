package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamCommands;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateSubCmd extends Subcommand {
    public CreateSubCmd() {
        super("create","Create a team", new Permission("gamelibrary.team.create"));
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
        if (!(command instanceof TeamCommands)) {
            throw new IllegalArgumentException("Command is not an instance of TeamCommands");
        }
        final TeamCommands commands = (TeamCommands) this.command;
        new Team(teamName, prefix, color, commands.propertiesClass);
        SenderHelper.sendSuccess(player, "Team " + teamName + " created");
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        if (args.length == 2) {
            return List.of("teamName");
        }
        if (args.length == 3) {
            return List.of("prefix");
        }
        if (args.length == 4) {
            return Arrays.stream(ChatColor.values()).map(ChatColor::name).collect(Collectors.toList());
        }
        return null;
    }
}
