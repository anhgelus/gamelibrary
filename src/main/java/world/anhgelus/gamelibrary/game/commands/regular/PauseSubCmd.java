package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.game.engine.GameState;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class PauseSubCmd extends Subcommand {
    private final Game game;

    public PauseSubCmd(Game game, GameProperties gameProperties) {
        super("pause", "Pause the game", gameProperties, new Permission(gameProperties.name + ".game.pause"));
        this.game = game;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (game.getEngine().getState() != GameState.RUNNING) {
            SenderHelper.sendWarning(player, "The game is not running");
            return true;
        }
        game.pause(player);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        return null;
    }
}
