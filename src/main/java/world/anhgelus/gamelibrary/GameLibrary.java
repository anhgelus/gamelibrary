package world.anhgelus.gamelibrary;

import org.bukkit.plugin.java.JavaPlugin;
import world.anhgelus.gamelibrary.util.config.ConfigAPI;

public final class GameLibrary extends JavaPlugin {

    private static GameLibrary instance;

    @Override
    public void onEnable() {
        instance = this;

        ConfigAPI.init(this);

        this.getLogger().info("GameLibrary has been loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("GameLibrary has been unloaded!");
    }

    public static GameLibrary getInstance() {
        return instance;
    }
}
