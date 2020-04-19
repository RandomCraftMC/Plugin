package me.tsblock.randomcraft.plugin;

import me.tsblock.randomcraft.plugin.Command.CommandManager;
import me.tsblock.randomcraft.plugin.Config.BaseConfig;
import me.tsblock.randomcraft.plugin.Config.MapConfig;
import me.tsblock.randomcraft.plugin.Listeners.ItemFrame;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomCraftPlugin extends JavaPlugin {
    private CommandManager commandManager;
    private MapConfig mapConfig;

    @Override
    public void onEnable() {
        setupConfig();
        registerEvents();
        setupCommands();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void setupConfig() {
        mapConfig = new MapConfig(this);
        mapConfig.setup();
    }

    public void saveConfig() {
        mapConfig.save();
    }

    public MapConfig getMapConfig() {
        return mapConfig;
    }

    private void setupCommands() {
        commandManager = new CommandManager(this);
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ItemFrame(), this);
    }
}