package world.anhgelus.gamelibrary.team;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.GeneralCommand;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class TeamCommands extends GeneralCommand {

    public final String propertiesClass;

    /**
     * @param subcommands Subcommands of the command
     * @param propertiesClass The property class of the teams
     */
    public TeamCommands(List<Subcommand> subcommands, String propertiesClass) {
        super(subcommands);
        this.propertiesClass = propertiesClass;
    }

    @Override
    protected boolean command(Player player, String label, String[] args) {
        if (!onSubcommand(player, label, args)) {
            SenderHelper.sendWarning(player, "Unknown subcommand: " + args[0]);
        }

        return true;
    }
}
