package me.tsblock.randomcraft.plugin.Config;

import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class BaseConfig {
    private final String configName;
    File file;
    FileConfiguration config;
    RandomCraftPlugin plugin;

    public BaseConfig(String configName, RandomCraftPlugin plugin) {
        this.configName = configName;
        this.plugin = plugin;
    }

    public void createFile() {
        file = new File(plugin.getDataFolder(), configName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        config.options().copyDefaults(true);
        save();
    }

    public abstract void setup();

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
