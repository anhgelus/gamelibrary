package world.anhgelus.gamelibrary;

import org.bukkit.plugin.java.JavaPlugin;
import world.anhgelus.gamelibrary.game.commands.GameCommandManager;
import world.anhgelus.gamelibrary.messages.MessageManager;
import world.anhgelus.gamelibrary.util.Vault;
import world.anhgelus.gamelibrary.util.config.Config;
import world.anhgelus.gamelibrary.util.config.ConfigAPI;

import java.util.HashMap;

public final class GameLibrary extends JavaPlugin {

    private static GameLibrary instance;

    @Override
    public void onEnable() {
        instance = this;

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
        return instance;
    }

    private void generateConfigs() {
        final Config messages = ConfigAPI.getConfig("messages.yml");
        if (messages.isFirstLoad()) {
            MessageManager.generateConfig(messages);
        }
        MessageManager.setupMessage(messages);
    }

    private void registerCommands() {
        // Nothing to do here now
    }

    private void generateVault() {
        Vault.setVault(new HashMap<>());
    }
}
