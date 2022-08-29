package world.anhgelus.gamelibrary.game.commands;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.GeneralCommands;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class GameCommands extends GeneralCommands {
    public GameCommands(List<Subcommand> subcommands) {
        super(subcommands);
    }

    @Override
    public boolean command(Player player, String[] args) {
        if (!onSubcommand(player, args)) {
            SenderHelper.sendWarning(player, "Unknown subcommand: " + args[0]);
        }

        return true;
    }

    @Override
    protected void helpCommands(Player player) {
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
