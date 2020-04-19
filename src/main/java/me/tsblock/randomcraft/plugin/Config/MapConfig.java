package me.tsblock.randomcraft.plugin.Config;

import me.tsblock.randomcraft.plugin.RandomCraftPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MapConfig extends BaseConfig {

    public MapConfig(RandomCraftPlugin plugin) {
        super("maps", plugin);
    }

    @Override
    public void setup() {
        if (getConfig().get("maps") == null) {
            return;
        }
        createFile();
        File mapFolder = new File("world/data/");
        File[] mapFiles = mapFolder.listFiles();
        HashMap<Integer, String> mapHashMap = new HashMap<>();
        for (File mapFile : mapFiles) {
            if (mapFile.isFile() & mapFile.getName().startsWith("map_")) {
                String mapIDString = mapFile.getName().replaceAll("[^0-9]", ""); // regex lol
                int mapID = Integer.parseInt(mapIDString);
                if (mapID >= 10000) { // yeah i know it's hardcoded but guess what? it's only made for randomcraft, so who cares.
                    mapHashMap.put(mapID, "");
                }
            }
        }
        getConfig().set("maps", mapHashMap);
        save();
    }

    @Override
    public void reload() {
        File mapFolder = new File("world/data/");
        File[] mapFiles = mapFolder.listFiles();
        HashMap<Integer, String> mapHashMap = (HashMap<Integer, String>) getConfig().get("maps");
        for (File mapFile : mapFiles) {
            if (mapFile.isFile() & mapFile.getName().startsWith("map_")) {
                String mapIDString = mapFile.getName().replaceAll("[^0-9]", ""); // regex lol
                int mapID = Integer.parseInt(mapIDString);
                if (mapHashMap.get(mapID) == null && mapID >= 10000) {
                    mapHashMap.put(mapID, "");
                }
            }
        }
        getConfig().set("maps", mapHashMap);
        config = YamlConfiguration.loadConfiguration(file);
    }
}

