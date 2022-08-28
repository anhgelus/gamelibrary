package world.anhgelus.gamelibrary.util.serializer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerSerializer {
    /**
     * Serialize a player to a string.
     * @param player Player to serialize
     * @return String representation of the player
     */
    public static String playerToString(Player player) {
        return player.getUniqueId().toString();
    }

    /**
     * Serialize players to a string.
     * @param players Players to serialize
     * @return String representation of the players
     */
    public static String playerToString(List<Player> players) {
        final StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(playerToString(player));
            sb.append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * Deserialize a player from a string.
     * @param string String to deserialize
     * @return Player deserialized from the string or null
     */
    @Nullable
    public static Player stringToPlayer(String string) {
        try {
            final UUID uuid = UUID.fromString(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return Bukkit.getPlayer(string);
    }

    /**
     * Deserialize players from a string.
     * @param string String to deserialize
     * @return Players deserialized from the string
     */
    public static List<Player> stringToPlayers(String string) {
        final List<Player> players = new ArrayList<>();
        for (String player : string.split(",")) {
            players.add(stringToPlayer(player));
        }
        return players;
    }
}
