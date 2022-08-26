package world.anhgelus.gamelibrary.game.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;
import java.util.Objects;

public class GameCommands implements CommandExecutor {
    private final List<Subcommand> subcommands;

    public GameCommands(List<Subcommand> subcommands) {
        this.subcommands = subcommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player)) return false;

        if (args.length == 0) {
            helpCommands(player);
            return true;
        }

        final String sub = args[0];

        for (Subcommand subcommand : subcommands) {
            if (subcommand.getIdentifier().equals(sub)) {
                if (!subcommand.onCommand(player, args)) {
                    SenderHelper.sendMessage(player, ChatColor.RED + "Error while executing the subcommand.");
                };
                return true;
            }
        }

        if (Objects.equals(sub, "help")) {
            helpCommands(player);
            return true;
        }

        SenderHelper.sendWarning(player, "Unknown subcommand: " + sub);

        return true;
    }

    private void helpCommands(Player player) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for (Subcommand subcommand : subcommands) {
            sb.append(SenderHelper.EXAMPLE)
              .append("/game ")
              .append(subcommand.getIdentifier())
              .append(SenderHelper.SUCCESS)
              .append(" - ")
              .append(SenderHelper.INFO)
              .append(subcommand.getDescription())
              .append("\n");
        }
        player.sendMessage(sb.toString());
    }
}
