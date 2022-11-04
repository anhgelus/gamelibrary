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
     * Parse a message
     * @param message Message to parse
     * @param player Player who is parsing the message
     * @param game Game's name
     * @return Parsed message
     */
    public static String parseMessage(String message, Player player, Game game) {
        while (message.contains("%game_name%")) {
            message = message.replace("%game_name%", game.getName());
        }
        if (customMessageParsers == null) {
            return message;
        }
        for (MessageParser parser : customMessageParsers) {
            while (message.contains(parser.placeholder)) {
                message = parser.parseMessage(message, player, game);
            }
        }
        return message;
    }

    public static List<MessageParser> getCustomMessageParsers() {
        return customMessageParsers;
    }

    public static void addCustomMessageParsers(MessageParser parser) {
        customMessageParsers.add(parser);
    }
}
