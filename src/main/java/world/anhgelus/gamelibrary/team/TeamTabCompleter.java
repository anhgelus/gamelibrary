package world.anhgelus.gamelibrary.team;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.GeneralTabCompleter;
import world.anhgelus.gamelibrary.commands.Subcommand;

import java.util.List;

public class TeamTabCompleter extends GeneralTabCompleter {
    public TeamTabCompleter(List<Subcommand> subcommands) {
        super(subcommands);
    }

    @Override
    protected List<String> complete(Player player, Subcommand subcommand, String[] args) {
        return onSubcommand(player, subcommand, args);
    }
}
