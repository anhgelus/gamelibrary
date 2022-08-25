package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.commands.Subcommand;

public class PauseSubCmd extends Subcommand {

    public PauseSubCmd() {
        super("pause", "Pause the game");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        // Pause the game
        return true;
    }
}
