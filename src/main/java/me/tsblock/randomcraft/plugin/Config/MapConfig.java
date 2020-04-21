package me.tsblock.randomcraft.plugin.Config;

import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class MapConfig extends BaseConfig {

    public MapConfig(RandomCraftPlugin plugin) {
        super("maps", plugin);
    }

    @Override
    public void setup() {
        File mapFolder = new File("world/data/");
        File[] mapFiles = mapFolder.listFiles();
        HashMap<Integer, String> mapsHashMap = new HashMap<>();
        for (File mapFile : mapFiles) {
            if (mapFile.isFile() & mapFile.getName().startsWith("map_")) {
                String mapIDString = mapFile.getName().replaceAll("[^0-9]", ""); // regex lol
                int mapID = Integer.parseInt(mapIDString);
                if (mapID >= 10000) {
                    mapsHashMap.put(mapID, "");
                    plugin.getServer().getConsoleSender().sendMessage(mapID + " " + mapsHashMap.get(mapID));
                }
            }
        }
        getConfig().set("maps", mapsHashMap);
        save();
    }

    @Override
    public void reload() {
        File mapFolder = new File("world/data/");
        File[] mapFiles = mapFolder.listFiles();
        /* add new maps to config */
        HashMap<String, Object> mapHashMap = getHashMap("maps");
        for (File mapFile : mapFiles) {
            if (mapFile.isFile() & mapFile.getName().startsWith("map_")) {
                String mapIDString = mapFile.getName().replaceAll("[^0-9]", ""); // regex lol
                int mapID = Integer.parseInt(mapIDString);
                if (mapHashMap.get(mapID) == null && mapID >= 10000) {
                    mapHashMap.put(String.valueOf(mapID), "");
                }
            }
        }
        setHashMap("maps", mapHashMap);
        config = YamlConfiguration.loadConfiguration(file);
    }
}

