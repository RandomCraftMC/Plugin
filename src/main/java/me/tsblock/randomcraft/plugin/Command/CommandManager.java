package me.tsblock.randomcraft.plugin.Command;

import co.aikar.commands.PaperCommandManager;
import me.tsblock.randomcraft.plugin.RandomCraftPlugin;

public class CommandManager {
    private RandomCraftPlugin plugin;
    private PaperCommandManager commandManager;

    public CommandManager(RandomCraftPlugin plugin) {
        this.plugin = plugin;
        this.commandManager = new PaperCommandManager(plugin);
        commandManager.enableUnstableAPI("help");
        registerCommands();
        registerCommandCompletion();
    }

    public void registerCommands() {
        commandManager.registerCommand(new MapCommand(plugin));
    }

    public void registerCommandCompletion() {

    }

}
