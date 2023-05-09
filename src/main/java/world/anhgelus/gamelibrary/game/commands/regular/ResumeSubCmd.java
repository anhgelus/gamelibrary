package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.game.GameState;
import world.anhgelus.gamelibrary.util.SenderHelper;

import java.util.List;

public class ResumeSubCmd extends Subcommand {
    private final Game game;

    public ResumeSubCmd(Game game, GameProperties gameProperties) {
        super("resume", "Resume the game", gameProperties, new Permission(gameProperties.name() + ".game.resume"));
        this.game = game;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (game.getState() != GameState.PAUSED) {
            SenderHelper.sendWarning(player, "The game is not paused");
            return true;
        }
        game.resume(player);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        return null;
    }
}
