package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.commands.Subcommand;

public class ResumeSubCmd extends Subcommand {

    public ResumeSubCmd() {
        super("resume", "Resume the game");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        // Resume the game
        return true;
    }
}
