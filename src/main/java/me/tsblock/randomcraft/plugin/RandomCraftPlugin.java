package me.tsblock.randomcraft.plugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.tsblock.randomcraft.plugin.Command.CommandManager;
import me.tsblock.randomcraft.plugin.Config.MapConfig;
import me.tsblock.randomcraft.plugin.Listeners.ItemFrameProtection;
import me.tsblock.randomcraft.plugin.Listeners.SendOpPackets;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomCraftPlugin extends JavaPlugin {
    private CommandManager commandManager;
    private MapConfig mapConfig;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        setupConfig();
        registerEvents();
        setupCommands();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void saveConfig() {
        mapConfig.save();
    }

    public MapConfig getMapConfig() {
        return mapConfig;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    private void setupCommands() {
        commandManager = new CommandManager(this);
    }

    private void setupConfig() {
        mapConfig = new MapConfig(this);
        mapConfig.setup();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ItemFrameProtection(), this);
        getServer().getPluginManager().registerEvents(new SendOpPackets(this), this);
    }
}