package p1xel.holyjoin.Storage;

import p1xel.holyjoin.HolyJoin;

public class Config {

    public static String getVersion() {
        return HolyJoin.getInstance().getConfig().getString("Version");
    }

    public static String getLanguage() {
        return HolyJoin.getInstance().getConfig().getString("Language");
    }

    public static boolean getBool(String path) { return HolyJoin.getInstance().getConfig().getBoolean(path); }
}
