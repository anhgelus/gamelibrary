package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.commands.Subcommand;

public class StartSubCmd extends Subcommand {
    public StartSubCmd() {
        super("start", "Start the game");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        // Start the game
        return true;
    }
}
