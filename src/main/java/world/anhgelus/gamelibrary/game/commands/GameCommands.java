package world.anhgelus.gamelibrary.game.commands;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.GeneralCommand;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class GameCommands extends GeneralCommand {
    public GameCommands(List<Subcommand> subcommands) {
        super(subcommands);
    }

    @Override
    public boolean command(Player player, String label, String[] args) {
        if (!onSubcommand(player, label, args)) {
            SenderHelper.sendWarning(player, "Unknown subcommand: " + args[0]);
        }

        return true;
    }
}
