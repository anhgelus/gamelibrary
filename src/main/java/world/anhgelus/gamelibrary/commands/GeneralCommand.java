package world.anhgelus.gamelibrary.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;
import java.util.Objects;

public abstract class GeneralCommand implements CommandExecutor {
    protected final List<Subcommand> subcommands;

    public GeneralCommand(List<Subcommand> subcommands) {
        this.subcommands = subcommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof final Player player)) return false;

        if (args.length == 0) {
            helpCommands(player, label);
            return true;
        }

        return command(player, label, args);
    }

    /**
     * Execute the subcommand
     * @param player The player who executed the command
     * @param label The label of the command
     * @param args The arguments passed to the command
     * @return True if the command was executed successfully
     */
    protected boolean onSubcommand(Player player, String label, String[] args) {
        final String sub = args[0];

        for (Subcommand subcommand : subcommands) {
            if (subcommand.identifier.equals(sub)) {
                if (!subcommand.permission.hasPermission(player)) {
                    SenderHelper.sendError(player, "You don't have the permission to execute this command.");
                    return true;
                }
                if (!subcommand.onCommand(player, args)) {
                    SenderHelper.sendError(player, "Error while executing the subcommand.");
                }
                return true;
            }
        }

        if (Objects.equals(sub, "help")) {
            helpCommands(player, label);
            return true;
        }

        return false;
    }

    protected abstract boolean command(Player player, String label, String[] args);

    /**
     * Execute the help command
     * @param player The player who executed the command
     */
    protected void helpCommands(Player player, String label) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for (Subcommand subcommand : subcommands) {
            sb.append(SenderHelper.EXAMPLE)
                    .append("/")
                    .append(label)
                    .append(" ")
                    .append(subcommand.identifier)
                    .append(SenderHelper.SUCCESS)
                    .append(" - ")
                    .append(SenderHelper.INFO)
                    .append(subcommand.description)
                    .append("\n");
        }
        player.sendMessage(sb.toString());
    }

    /**
     * Send a list to a player
     * @param player The player to send the list to
     * @param list The list to send
     */
    public static void sendList(Player player, List list) {
        final StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o.toString()).append(", ");
        }
        player.sendMessage(sb.toString().substring(0, sb.toString().length() - 2));
    }

    /**
     * Generate a generic tab completer with automatic completion
     * @return The generic tab completer
     */
    public GeneralTabCompleter getGenericTabCompleter() {
        return new GeneralTabCompleter(subcommands) {
            @Override
            protected List<String> complete(Player player, Subcommand subcommand, String[] args) {
                return onSubcommand(player, subcommand, args);
            }
        };
    }
}
