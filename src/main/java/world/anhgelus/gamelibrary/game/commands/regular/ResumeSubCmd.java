package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.game.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class ResumeSubCmd extends Subcommand {
    public ResumeSubCmd(GameProperties gameProperties) {
        super("resume", "Resume the game", gameProperties);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        Game game;
        try {
            game = Game.getInstance();
        } catch (NullPointerException e) {
            SenderHelper.sendError(player, "Game is not started yet.");
            return true;
        }
        game.resume(player);
        return true;
    }
}
