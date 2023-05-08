package world.anhgelus.gamelibrary;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.messages.Message;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.team.TeamCommands;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.team.subcommands.*;
import world.anhgelus.gamelibrary.util.config.Config;
import world.anhgelus.gamelibrary.util.config.ConfigAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class GameLibrary extends JavaPlugin {

    private static GameLibrary INSTANCE;
    private static Message GAME_MESSAGES;
    private static ConfigAPI CONFIG_API;
    private static Logger LOGGER;
    private List<Subcommand> teamSubcommands;

    @Override
    public void onEnable() {
        INSTANCE = this;
        LOGGER = this.getLogger();

        CONFIG_API = new ConfigAPI(this);

        TeamManager.CONFIG = CONFIG_API.getConfig("teams");

        teamSubcommands = new ArrayList<>();
        teamSubcommands.add(new CreateSubCmd());
        teamSubcommands.add(new ListSubCmd());
        teamSubcommands.add(new JoinSubCmd());
        teamSubcommands.add(new LeaveSubCmd());
        teamSubcommands.add(new SaveSubCmd());

        this.generateConfigs();
        this.registerCommands();

        LOGGER.info("GameLibrary has been loaded!");
    }

    @Override
    public void onDisable() {
        LOGGER.info("GameLibrary has been unloaded!");
    }

    public static GameLibrary getInstance() {
        return INSTANCE;
    }
    public static Logger getLOGGER() {
        return LOGGER;
    }
    public static Message getGameMessages() {
        return GAME_MESSAGES;
    }

    public static ConfigAPI getConfigApi() {
        return CONFIG_API;
    }

    private void generateConfigs() {
        final Config messagesConfig = CONFIG_API.getConfig("messages");
        GAME_MESSAGES = MessageManager.setupGameMessage(messagesConfig);
    }

    private void registerCommands() {
        final PluginCommand teamCommand = getCommand("team");
        if (teamCommand != null) {
            final TeamCommands teamCommands = new TeamCommands(teamSubcommands);
            teamCommand.setExecutor(teamCommands);
            teamCommand.setTabCompleter(teamCommands.getGenericTabCompleter());
        }
    }
}
