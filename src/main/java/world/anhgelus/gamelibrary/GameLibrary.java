package world.anhgelus.gamelibrary;

import org.bukkit.plugin.java.JavaPlugin;
import world.anhgelus.gamelibrary.messages.Message;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.util.Vault;
import world.anhgelus.gamelibrary.util.config.Config;
import world.anhgelus.gamelibrary.util.config.ConfigAPI;

import java.util.HashMap;

public final class GameLibrary extends JavaPlugin {

    private static GameLibrary INSTANCE;
    private static Message GAME_MESSAGES;

    @Override
    public void onEnable() {
        INSTANCE = this;

        ConfigAPI.init(this);

        this.generateConfigs();
        this.generateVault();
        this.registerCommands();

        this.getLogger().info("GameLibrary has been loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("GameLibrary has been unloaded!");
    }

    public static GameLibrary getInstance() {
        return INSTANCE;
    }
    public static Message getGameMessages() {
        return GAME_MESSAGES;
    }

    private void generateConfigs() {
        final Config messagesConfig = ConfigAPI.getConfig("messages.yml");
        if (messagesConfig.isFirstLoad()) {
            MessageManager.generateGameConfig(messagesConfig);
        }
        final Message message = MessageManager.setupGameMessage(messagesConfig);
    }

    private void registerCommands() {
        // Nothing to do here now
    }

    private void generateVault() {
        Vault.setVault(new HashMap<>());
    }
}
