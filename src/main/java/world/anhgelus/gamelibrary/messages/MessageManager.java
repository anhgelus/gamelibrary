package world.anhgelus.gamelibrary.messages;

import org.bukkit.configuration.file.FileConfiguration;
import world.anhgelus.gamelibrary.util.config.Config;

public class MessageManager {
    /**
     * Set up the message object
     * @param messageConfig The configuration
     */
    public static void setupMessage(Config messageConfig) {
        final FileConfiguration config = messageConfig.get();
        Message.start = config.getString("start");
        Message.start_creator = config.getString("start_creator");
        Message.end = config.getString("end");
        Message.end_creator = config.getString("end_creator");
        Message.pause = config.getString("pause");
        Message.pause_creator = config.getString("pause_creator");
        Message.resume = config.getString("resume");
        Message.resume_creator = config.getString("resume_creator");
    }

    /**
     * Generate the configuration file for the messages
     * @param messageConfig Configuration of the messages
     */
    public static void generateConfig(Config messageConfig) {
        final FileConfiguration config = messageConfig.get();
        config.set("start", "&aGame &6%game% &ahas been started!");
        config.set("start_creator", "&aGame &6%game% &ahas been started by &6%creator%&a!");
        config.set("end", "&aGame &6%game% &ahas been stopped!");
        config.set("end_creator", "&aGame &6%game% &ahas been stopped by &6%creator%&a!");
        config.set("pause", "&aGame &6%game% &ahas been paused!");
        config.set("pause_creator", "&aGame &6%game% &ahas been paused by &6%creator%&a!");
        config.set("resume", "&aGame &6%game% &ahas been resumed!");
        config.set("resume_creator", "&aGame &6%game% &ahas been resumed by &6%creator%&a!");
        messageConfig.save();
    }

    /**
     * Parse a message
     * @param message Message to parse
     * @param game Game's name
     * @param creator Game's creator
     * @return Parsed message
     */
    public static String parseMessage(String message, String game, String creator) {
        return message.replace("%game%", game).replace("%creator%", creator);
    }
}
