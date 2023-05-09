package world.anhgelus.gamelibrary.game.commands;

import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.game.commands.regular.PauseSubCmd;
import world.anhgelus.gamelibrary.game.commands.regular.ResumeSubCmd;
import world.anhgelus.gamelibrary.game.commands.regular.StartSubCmd;
import world.anhgelus.gamelibrary.game.commands.regular.StopSubCmd;

import java.util.ArrayList;
import java.util.List;

public class GameCommandManager {
    private final List<Subcommand> subcommands;
    public final GameProperties gameProperties;
    public final Game game;

    public GameCommandManager(Game game, GameProperties properties) {
        subcommands = generateList(game,properties);
        gameProperties = properties;
        this.game = game;
    }

    /**
     * Register the class GameCommands
     * You must use this if you want to use the custom game commands!
     * @return GameCommands to register in the main file
     */
    public GameCommands registerGameCommands() {
        return new GameCommands(subcommands);
    }

    /**
     * Add a subcommand to the list
     * @param subcommand Subcommand to add to the list
     */
    public void registerSubcommand(Subcommand subcommand) {
        subcommands.add(subcommand);
    }

    /**
     * Remove a subcommand from the list
     * @param subcommand Subcommand to remove from the list
     */
    public void unregisterSubcommand(Subcommand subcommand) {
        subcommands.remove(subcommand);
    }

    public List<Subcommand> getSubcommands() {
        return subcommands;
    }

    /**
     * Generate a list of subcommands
     * @return List of subcommands
     */
    private static List<Subcommand> generateList(Game game, GameProperties properties) {
        final List<Subcommand> list = new ArrayList<>();
        list.add(new StartSubCmd(game, properties));
        list.add(new StopSubCmd(game,properties));
        list.add(new PauseSubCmd(game,properties));
        list.add(new ResumeSubCmd(game,properties));
        return list;
    }
}
