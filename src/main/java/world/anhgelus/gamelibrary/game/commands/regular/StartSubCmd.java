package world.anhgelus.gamelibrary.game.commands.regular;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.GameLibrary;
import world.anhgelus.gamelibrary.commands.Permission;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.commands.Subcommand;

import java.util.List;

public class StartSubCmd extends Subcommand {
    public StartSubCmd(GameProperties gameProperties) {
        super("start", "Start the game", gameProperties, new Permission(gameProperties.name + ".game.start"));
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        final Game game = new Game(GameLibrary.getInstance(), properties.name);
        game.start(player);
        return true;
    }

    @Override
    public List<String> getTabCompleter(Player player, String[] args) {
        return null;
    }
}
