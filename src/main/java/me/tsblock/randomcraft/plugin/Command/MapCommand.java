package me.tsblock.randomcraft.plugin.Command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("map|m")
@CommandPermission("randomcraft.admin")
public class MapCommand extends BaseCommand {
    private RandomCraftPlugin plugin;

    public MapCommand(RandomCraftPlugin plugin) {
        this.plugin = plugin;
    }

    @Default
    @Subcommand("give")
    @Syntax("<map id>/<multiple map ids>")
//    @CommandCompletion("@mapid")
    @Description("Give yourself a map from id")
    public static void give(Player player, String[] args) {
        player.sendMessage("cheese");
    }

    @HelpCommand
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @Description("Reload the map config")
    public void reload(Player player) {
        plugin.getMapConfig().reload();
        sendMessage(player, "Reloaded config.");
    }

    private void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.AQUA + "[Map]" + " " + ChatColor.GREEN + message);
    }
}
