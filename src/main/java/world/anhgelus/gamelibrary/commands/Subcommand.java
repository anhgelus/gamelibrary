package world.anhgelus.gamelibrary.commands;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.game.GameProperties;

public abstract class Subcommand {
    /**
     * Identifier of the subcommand.
     * Example: hey for /game hey
     */
    private final String identifier;
    private final String description;
    protected final GameProperties properties;

    public Subcommand(String identifier, String description, GameProperties properties) {
        this.identifier = identifier;
        this.description = description;
        this.properties = properties;
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
