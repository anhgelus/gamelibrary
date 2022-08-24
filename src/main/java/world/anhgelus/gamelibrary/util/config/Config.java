package world.anhgelus.gamelibrary.util.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private final Plugin main;
    private final String name;

    private File database = null;
    private FileConfiguration databaseConfig = null;

    /**
     *
     * @param main Plugin's main file
     * @param name Name of the configuration
     */
    public Config(Plugin main, String name) {
        this.main = main;
        this.name = name;
        this.setup();
    }

    /**
     * Set up the configuration
     */
    public void setup() {
        if (this.database == null) database = new File(main.getDataFolder(), name + ".yml");
        if (!database.exists()) {
            if (database.getParentFile().exists()) database.getParentFile().mkdir();
            main.saveResource(name + ".yml", false);
        }
    }

    /**
     * Get the configuration
     * @return The configuration
     */
    public FileConfiguration get() {
        if (databaseConfig == null) reload();
        return databaseConfig;
    }

    /**
     * Save the configuration
     */
    public void save() {
        if (database == null || databaseConfig == null) return;
        try {
            get().save(database);
        } catch (IOException e) {
            final Logger logger = main.getLogger();
            logger.log(Level.SEVERE, "Error when saving the file " + name + ".yml");
            logger.log(Level.SEVERE, "This is a bug, please report it to the developer", e);
            logger.warning(e.getMessage());
        }
    }

    /**
     * Reload the configuration
     */
    public void reload() {
        if (this.database == null) database = new File(main.getDataFolder(), name + ".yml");
        this.databaseConfig = YamlConfiguration.loadConfiguration(database);

        InputStream defaultStream = main.getResource(name + ".yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.databaseConfig.setDefaults(defaultConfig);
        }
    }
}
