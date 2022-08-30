package world.anhgelus.gamelibrary.util.config;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * @author Robotv2
 * @author Modified by anhgelus
 */

public class ConfigAPI {
    private final Plugin plugin;
    private final HashMap<String, Config> CONFIGS = new HashMap<>();



    /**
     * @param plugin Plugin's main file
     */
    public ConfigAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Get the configuration
     * @param name Name of the configuration
     * @return The configuration
     */
    public Config getConfig(String name) {
        if(plugin == null) {
            throw new NullPointerException("plugin");
        }
        Config config = CONFIGS.get(name);
        if(config == null) {
            config = new Config(plugin, name);
            CONFIGS.put(name, config);
        }
        return config;
    }
}
