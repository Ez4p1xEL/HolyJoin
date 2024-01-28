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
        return get().isSet("groups." + key + ".join");
    }

    public static boolean isQuitExist(String key) {
        return get().isSet("groups." + key + ".quit");
    }

    public static boolean isActionExist(String key) {
        return get().isSet("groups." + key + ".action");
    }

    public static boolean isFirstJoinExist(String key) { return get().isSet("groups." + key + ".firstjoin"); }

    public static boolean getJoinDefaultSetting(String key) {
        return get().getBoolean("groups." + key + ".join.default");
    }

    public static List<String> getJoinMessage(String key) {
        return get().getStringList("groups." + key + ".join.message");
    }

    public static boolean getQuitDefaultSetting(String key) {
        return get().getBoolean("groups." + key + ".quit.default");
    }

    public static List<String> getQuitMessage(String key) {
        return get().getStringList("groups." + key + ".quit.message");
    }

    public static String getActionType(String key, String number) {
        return get().getString("groups." + key + ".action." + number + ".type");
    }

    public static String getActionValue(String key, String number, String name) {
        return get().getString("groups." + key + ".action." + number + "." + name);
    }

    public static String getfirstJoinType(String key, String number) {
        return get().getString("groups." + key + ".firstjoin." + number + ".type");
    }

    public static String getfirstJoinValue(String key, String number, String name) {
        return get().getString("groups." + key + ".firstjoin." + number + "." + name);
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
