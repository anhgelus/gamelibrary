package world.anhgelus.gamelibrary.team;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.GeneralCommands;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class TeamCommands extends GeneralCommands {

    public TeamCommands(List<Subcommand> subcommands) {
        super(subcommands);
    }

    @Override
    protected boolean command(Player player, String[] args) {
        if (!onSubcommand(player, args)) {
            SenderHelper.sendWarning(player, "Unknown subcommand: " + args[0]);
        }

        return true;
    }
}
