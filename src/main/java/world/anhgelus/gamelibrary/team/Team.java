package world.anhgelus.gamelibrary.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
    private final String name;
    private final UUID uuid;
    private final String prefix;
    private final ChatColor color;
    private final List<Player> players;

    public Team(String name, String prefix, ChatColor color) {
        this.name = name;
        this.uuid = UUID.fromString(name);
        this.prefix = prefix;
        this.color = color;
        this.players = new ArrayList<>();
    }

    /**
     * Generate the chat prefix
     * @return Chat prefix
     */
    public String generateChatPrefix() {
        return ChatColor.RESET + "[" + color + name + ChatColor.RESET + "]";
    }

    /**
     * Add a player to the team
     * @param player Player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Remove a player from the team
     * @param player Player to remove
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Check if a player is in the team
     * @return True if the player is in the team
     */
    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    /**
     * Add players to the team
     * @param players Players to set
     */
    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getColor() {
        return color;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String toString() {
        return name;
    }
}
