package me.tsblock.randomcraft.plugin.Config;

import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseConfig {
    private final String configName;
    File file;
    FileConfiguration config;
    RandomCraftPlugin plugin;

    public BaseConfig(String configName, RandomCraftPlugin plugin) {
        this.configName = configName;
        this.plugin = plugin;
        createFile();
    }

    public void createFile() {
        file = new File(plugin.getDataFolder(), configName + ".yml");
        boolean setup = false;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setup = true;
        }
        config = YamlConfiguration.loadConfiguration(file);
        if (setup) setup();
        save();
    }

    public abstract void setup();

    public FileConfiguration getConfig() {
        return config;
    }

    public HashMap<String, Object> getHashMap(String path) {
        HashMap<String, Object> map = new HashMap<>();
        if (!getConfig().contains(path)) {
            return map;
        }
        ConfigurationSection section = getConfig().getConfigurationSection(path);
        if (section != null) {
            for (String key : section.getKeys(false)) {
                map.put(key, section.get(key));
            }
        }
        return map;
    }

    public void setHashMap(String path, HashMap<String, Object> map) {
        ConfigurationSection section = getConfig().getConfigurationSection(path);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            section.set(String.valueOf(entry.getKey()), entry.getValue());
        }
        getConfig().set(path, section);
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
