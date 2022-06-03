package p1xel.holyjoin.Storage;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import p1xel.holyjoin.HolyJoin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GroupSettings {

    public static void createFile() {
        File file = new File(HolyJoin.getInstance().getDataFolder(), "groups.yml");
        if (!file.exists()) {
            HolyJoin.getInstance().saveResource("groups.yml", false);
        }
    }

    public static FileConfiguration get() {
        File file = new File(HolyJoin.getInstance().getDataFolder(), "groups.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        File file = new File(HolyJoin.getInstance().getDataFolder(), "groups.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set(path,value);
        try {
            yaml.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static boolean isJoinExist(String key) {
        return get().isSet(key + ".join");
    }

    public static boolean isQuitExist(String key) {
        return get().isSet(key + ".quit");
    }

    public static boolean isActionExist(String key) {
        return get().isSet(key + ".action");
    }

    public static boolean getJoinDefaultSetting(String key) {
        return get().getBoolean(key + ".join.default");
    }

    public static List<String> getJoinMessage(String key) {
        return get().getStringList(key + ".join.message");
    }

    public static boolean getQuitDefaultSetting(String key) {
        return get().getBoolean(key + ".quit.default");
    }

    public static List<String> getQuitMessage(String key) {
        return get().getStringList(key + ".quit.message");
    }

    public static String getActionType(String key, String number) {
        return get().getString(key + ".action." + number + ".type");
    }

    public static String getActionValue(String key, String number, String name) {
        return get().getString(key + ".action." + number + "." + name);
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
