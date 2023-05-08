package world.anhgelus.gamelibrary.messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.util.config.Config;

import javax.annotation.Nullable;
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
        message.setMessage("start", config.getString("engine.start"));
        message.setMessage("start_creator", config.getString("engine.start_creator"));
        message.setMessage("end", config.getString("engine.end"));
        message.setMessage("end_creator", config.getString("engine.end_creator"));
        message.setMessage("pause", config.getString("engine.pause"));
        message.setMessage("pause_creator", config.getString("engine.pause_creator"));
        message.setMessage("engine.resume", config.getString("engine.resume"));
        message.setMessage("resume_creator", config.getString("engine.resume_creator"));
        message.setMessage("point_to_team", config.getString("game.point_to_team"));
        message.setMessage("remove_point_to_team", config.getString("game.remove_point_to_team"));
        return message;
    }

    /**
     * Parse a message
     * @param message Message to parse
     * @param player Player who is parsing the message
     * @param game Game
     * @param team Team
     * @return Parsed message
     */
    public static String parseMessage(String message, @Nullable Player player, Game game, @Nullable Team team) {
        replace(message, "%game_name%", game.getName());
        if (player != null) {
            replace(message, "%player_name%", player.getName());
        }
        if (team != null) {
            replace(message, "%team_name%", team.name);
            replace(message, "%team_color%", team.color.toString());
            replace(message, "%team_points%", team.getPoints());
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

    /**
     * Parse a message
     * @param message Message to parse
     * @param player Player who is parsing the message
     * @param game Game
     * @return Parsed message
     */
    public static String parseMessage(String message, Player player, Game game) {
        return parseMessage(message, player, game, null);
    }
    /**
     * Parse a message
     * @param message Message to parse
     * @param game Game
     * @return Parsed message
     */
    public static String parseMessage(String message, Game game) {
        return parseMessage(message, game, null);
    }
    /**
     * Parse a message
     * @param message Message to parse
     * @param game Game
     * @param team Team
     * @return Parsed message
     */
    public static String parseMessage(String message, Game game, Team team) {
        return parseMessage(message, null, game, team);
    }

    public static List<MessageParser> getCustomMessageParsers() {
        return customMessageParsers;
    }

    public static void addCustomMessageParsers(MessageParser parser) {
        customMessageParsers.add(parser);
    }

    private static String replace(String message, String target, String replacement) {
        while (message.contains(target)) {
            message = message.replace(target, replacement);
        }
        return message;
    }

    private static String replace(String message, String target, int replacement) {
        return replace(message, target, String.valueOf(replacement));
    }
}
