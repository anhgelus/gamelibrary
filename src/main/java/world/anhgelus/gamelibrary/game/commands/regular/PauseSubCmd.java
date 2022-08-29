package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class PauseSubCmd extends Subcommand {
    public PauseSubCmd(GameProperties gameProperties) {
        super("pause", "Pause the game", gameProperties);
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
        game.pause(player);
        return true;
    }
}
