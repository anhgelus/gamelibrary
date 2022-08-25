package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.commands.Subcommand;

public class StopSubCmd extends Subcommand {
    public StopSubCmd() {
        super("stop", "Stop the game");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        // Stop the game
        return true;
    }
}
