package world.anhgelus.gamelibrary.game.commands;

import org.bukkit.entity.Player;

public abstract class Subcommand {
    /**
     * Identifier of the subcommand.
     * Example: hey for /game hey
     */
    private final String identifier;
    private final String description;

    public Subcommand(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    /**
     * When the subcommand is executed.
     * @param player Player who executed the subcommand.
     * @param args Arguments of the subcommand. The first args is the identifier of the subcommand.
     * @return True if the subcommand was executed successfully like spigot CommandsExecutor::onCommand.
     */
    public abstract boolean onCommand(Player player, String[] args);
}
