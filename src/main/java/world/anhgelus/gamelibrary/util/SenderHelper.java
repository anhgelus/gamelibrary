package world.anhgelus.gamelibrary.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SenderHelper {
    public static ChatColor SUCCESS = ChatColor.GREEN;
    public static ChatColor ERROR = ChatColor.RED;
    public static ChatColor WARNING = ChatColor.YELLOW;
    public static ChatColor INFO = ChatColor.BLUE;
    public static ChatColor DEFAULT = ChatColor.WHITE;
    public static ChatColor EXAMPLE = ChatColor.DARK_GREEN;

    /**
     * Send a message to a player
     * @param player Player to send the message
     * @param message Message to send
     * @param color Color of the message
     */
    private static void sendMessage(Player player, ChatColor color, String message) {
        player.sendMessage(color + message);
    }

    /**
     * Send a success message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendSuccess(Player player, String message) {
        sendMessage(player, SUCCESS, message);
    }

    /**
     * Send an error message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendError(Player player, String message) {
        sendMessage(player, ERROR, message);
    }

    /**
     * Send a warning message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendWarning(Player player, String message) {
        sendMessage(player, WARNING, message);
    }

    /**
     * Send an info message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendInfo(Player player, String message) {
        sendMessage(player, INFO, message);
    }

    /**
     * Send an example message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendExample(Player player, String message) {
        sendMessage(player, EXAMPLE, message);
    }

    /**
     * Send a message to a player
     * @param player Player to send the message
     * @param message Message to send
     */
    public static void sendMessage(Player player, String message) {
        sendMessage(player, DEFAULT, message);
    }

    /**
     * Send a broadcast
     * @param color Color of the message
     * @param message Message to send
     */
    private static void broadcastMessage(ChatColor color, String message) {
        Bukkit.broadcastMessage(color + message);
    }

    /**
     * Send a success broadcast
     * @param message Message to send
     */
    public static void broadcastSuccess(String message) {
        broadcastMessage(SUCCESS, message);
    }

    /**
     * Send an error broadcast
     * @param message Message to send
     */
    public static void broadcastError(String message) {
        broadcastMessage(ERROR, message);
    }

    /**
     * Send a warning broadcast
     * @param message Message to send
     */
    public static void broadcastWarning(String message) {
        broadcastMessage(WARNING, message);
    }

    /**
     * Send an info broadcast
     * @param message Message to send
     */
    public static void broadcastInfo(String message) {
        broadcastMessage(INFO, message);
    }

    /**
     * Send an example broadcast
     * @param message Message to send
     */
    public static void broadcastExample(String message) {
        broadcastMessage(EXAMPLE, message);
    }

    /**
     * Send a broadcast
     * @param message Message to send
     */
    public static void broadcastMessage(String message) {
        broadcastMessage(DEFAULT, message);
    }

}
