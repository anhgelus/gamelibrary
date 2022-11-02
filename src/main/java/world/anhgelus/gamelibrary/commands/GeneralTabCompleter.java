package world.anhgelus.gamelibrary.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public abstract class GeneralTabCompleter implements TabCompleter {
    protected final List<Subcommand> subcommands;

    protected GeneralTabCompleter(List<Subcommand> subcommands) {
        this.subcommands = subcommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof final Player player)) {
            return null;
        }

        // Return list of subcommands
        if (args.length == 0) {
            return subcommands.stream().map(Subcommand::getIdentifier).toList();
        }

        for (Subcommand subcommand : subcommands) {
            if (subcommand.identifier.equals(args[0])) {
                return complete(player, subcommand, args);
            }
        }

        return null;
    }

    protected abstract List<String> complete(Player player, Subcommand subcommand, String[] args);

    /**
     * Complete the subcommand
     * @param player The player who executed the command
     * @param subcommand The subcommand to complete
     * @param args The arguments passed to the command
     * @return A list of possible completions
     */
    @Nullable
    protected List<String> onSubcommand(Player player, Subcommand subcommand, String[] args) {
        for (Subcommand sub : subcommands) {
            if (sub.getIdentifier().equals(subcommand.getIdentifier())) {
                sub.getTabCompleter(player, args);
            }
        }
        return null;
    }
}
