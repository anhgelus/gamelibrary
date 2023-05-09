package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.game.GameState;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class StopSubCmd extends Subcommand {
    private final Game game;

    public StopSubCmd(Game game, GameProperties gameProperties) {
        super("stop", "Stop the game", gameProperties, new Permission(gameProperties.name() + ".game.stop"));
        this.game = game;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (game.getState() == GameState.NOT_STARTED) {
            SenderHelper.sendWarning(player, "The game is not started");
            return true;
        }
        game.stop(player);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        return null;
    }
}
