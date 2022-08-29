package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class StopSubCmd extends Subcommand {
    public StopSubCmd(GameProperties gameProperties) {
        super("stop", "Stop the game", gameProperties);
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
        game.stop(player);
        return true;
    }
}
