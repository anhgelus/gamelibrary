package world.anhgelus.gamelibrary.commands;

import org.bukkit.entity.Player;

public record Permission(String permission) {
    public boolean hasPermission(Player player) {
        return player.hasPermission(permission) || player.isOp();
    }

    public org.bukkit.permissions.Permission toBukkitPermission() {
        return new org.bukkit.permissions.Permission(permission);
    }
}
