package world.anhgelus.gamelibrary.messages;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.Game;

public abstract class MessageParser {
    final String placeholder;

    /**
     *
     * @param placeholder Placeholder to be replaced
     */
    public MessageParser(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * Parse the message and replace the placeholders with the values.
     * @param message Message to parse
     * @param player Player who is parsing the message
     * @param game Game's name
     * @return Parsed message
     */
    public abstract String parseMessage(String message, Player player, Game game);
}
