package world.anhgelus.gamelibrary.messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.util.config.Config;

import java.util.List;

public class MessageManager {
    private static List<MessageParser> customMessageParsers;

    /**
     * Set up the message object
     * @param messageConfig The configuration
     */
    public static Message setupGameMessage(Config messageConfig) {
        final FileConfiguration config = messageConfig.get();
        final Message message = new Message("game-commands");
        message.setMessage("start", config.getString("start"));
        message.setMessage("start_creator", config.getString("start_creator"));
        message.setMessage("end", config.getString("end"));
        message.setMessage("end_creator", config.getString("end_creator"));
        message.setMessage("pause", config.getString("pause"));
        message.setMessage("pause_creator", config.getString("pause_creator"));
        message.setMessage("resume", config.getString("resume"));
        message.setMessage("resume_creator", config.getString("resume_creator"));
        return message;
    }

    /**
     * Generate the configuration file for the messages
     * @param messageConfig Configuration of the messages
     */
    public static void generateGameConfig(Config messageConfig) {
        final FileConfiguration config = messageConfig.get();
        config.set("start", "&aGame &6%game_name% &ahas been started by &6%player_name%&a!");
        config.set("start_creator", "&aThe game &6%game_name% &ahas been started perfectly!");
        config.set("end", "&aGame &6%game_name% &ahas been stopped perfectly!");
        config.set("end_creator", "&aThe Game &6%game_name% &ahas been stopped perfectly!");
        config.set("pause", "&aGame &6%game_name% &ahas been paused by &6%player_name%&a!");
        config.set("pause_creator", "&aThe game &6%game_name% &ahas been paused perfectly!");
        config.set("resume", "&aThe game &6%game_name% &ahas been resumed by &6%player_name%&a!");
        config.set("resume_creator", "&aThe game &6%game_name% &ahas been resumed perfectly!");
        messageConfig.save();
    }

    /**
     * Parse a message
     * @param message Message to parse
     * @param player Player who is parsing the message
     * @param game Game's name
     * @return Parsed message
     */
    public static String parseMessage(String message, Player player, Game game) {
        if (message.contains("%game_name%")) {
            message = message.replace("%game_name%", game.getName());
        }
        if (message.contains("%player_name%")) {
            message = message.replace("%player_name%", player.getName());
        }
        for (MessageParser parser : customMessageParsers) {
            if (message.contains(parser.placeholder)) {
                message = parser.parseMessage(message, player, game);
            }
        }
        return message;
    }

    public static List<MessageParser> getCustomMessageParsers() {
        return customMessageParsers;
    }

    public static void setCustomMessageParsers(List<MessageParser> customMessageParsers) {
        MessageManager.customMessageParsers = customMessageParsers;
    }
}
