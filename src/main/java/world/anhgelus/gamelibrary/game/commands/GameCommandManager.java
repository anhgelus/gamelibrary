package world.anhgelus.gamelibrary.game.commands;

import world.anhgelus.gamelibrary.commands.Subcommand;
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

    public GameCommandManager(GameProperties properties) {
        subcommands = generateList(properties);
        gameProperties = properties;
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
     * Register the class GameTabCompleter
     * You must use this if you want to use the tab completer for custom game commands!
     * @deprecated Use {@link world.anhgelus.gamelibrary.game.commands.GameCommands#getGenericTabCompleter()} instead
     * @return GameTabCompleter to register in the main file
     */
    @Deprecated
    public GameTabCompleter registerGameTabCompleter() {
        return new GameTabCompleter(subcommands);
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
    private static List<Subcommand> generateList(GameProperties properties) {
        final List<Subcommand> list = new ArrayList<>();
        list.add(new StartSubCmd(properties));
        list.add(new StopSubCmd(properties));
        list.add(new PauseSubCmd(properties));
        list.add(new ResumeSubCmd(properties));
        return list;
    }
}
