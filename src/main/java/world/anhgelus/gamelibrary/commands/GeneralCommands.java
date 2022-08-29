package world.anhgelus.gamelibrary.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;
import java.util.Objects;

public abstract class GeneralCommands implements CommandExecutor {
    protected final List<Subcommand> subcommands;

    public GeneralCommands(List<Subcommand> subcommands) {
        this.subcommands = subcommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player)) return false;

        if (args.length == 0) {
            helpCommands(player);
            return true;
        }

        return command(player, args);
    }

    protected boolean onSubcommand(Player player, String[] args) {
        final String sub = args[0];

        for (Subcommand subcommand : subcommands) {
            if (subcommand.getIdentifier().equals(sub)) {
                if (!subcommand.onCommand(player, args)) {
                    SenderHelper.sendMessage(player, ChatColor.RED + "Error while executing the subcommand.");
                }
                return true;
            }
        }

        if (Objects.equals(sub, "help")) {
            helpCommands(player);
            return true;
        }

        return false;
    }

    protected abstract boolean command(Player player, String[] args);

    protected abstract void helpCommands(Player player);
}
