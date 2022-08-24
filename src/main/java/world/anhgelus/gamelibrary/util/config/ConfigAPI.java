package world.anhgelus.gamelibrary.util.config;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ConfigAPI {
    private static Plugin plugin;
    private static final HashMap<String, Config> CONFIGS = new HashMap<>();

    /**
     * Get the configuration
     * @param name Name of the configuration
     * @return The configuration
     */
    public static Config getConfig(String name) {
        if(plugin == null) {
            throw new NullPointerException("plugin");
        }
        Config config = CONFIGS.get(name);
        if(config == null) {
            config = new Config(ConfigAPI.plugin, name);
            CONFIGS.put(name, config);
        }
        return config;
    }

    /**
     * Init the ConfigAPI
     * @param plugin Plugin's main file
     */
    public static void init(Plugin plugin) {
        ConfigAPI.plugin = plugin;
    }
}
