package world.anhgelus.gamelibrary.team.subcommands;

import org.bukkit.entity.Player;
import world.anhgelus.gamelibrary.commands.Subcommand;
import world.anhgelus.gamelibrary.game.GameProperties;
import world.anhgelus.gamelibrary.team.Team;
import world.anhgelus.gamelibrary.team.TeamManager;
import world.anhgelus.gamelibrary.util.SenderHelper;

public class SaveSubCmd extends Subcommand {
    public SaveSubCmd() {
        super("save", "Save the team inside a configuration file", null);
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length > 2) {
            SenderHelper.sendWarning(player, "Usage: /team save <config>");
            return true;
        }
        TeamManager.saveTeam(TeamManager.getTeam(args[1]));
        SenderHelper.sendSuccess(player, "Team saved");
        return true;
    }
}
