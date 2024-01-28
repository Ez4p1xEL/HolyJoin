package p1xel.holyjoin;

import org.bukkit.plugin.java.JavaPlugin;
import p1xel.holyjoin.Command.Cmd;
import p1xel.holyjoin.Listeners.JoinCheck;
import p1xel.holyjoin.Listeners.QuitCheck;
import p1xel.holyjoin.SpigotMC.UpdateChecker;
import p1xel.holyjoin.Storage.Config;
import p1xel.holyjoin.Storage.GroupSettings;
import p1xel.holyjoin.Storage.Locale;
import p1xel.holyjoin.bStats.Metrics;

public class HolyJoin extends JavaPlugin {

    private static HolyJoin instance;
    public static HolyJoin getInstance() {return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Locale.createFile();
        GroupSettings.createFile();

        getServer().getPluginCommand("HolyJoin").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new JoinCheck(), this);
        getServer().getPluginManager().registerEvents(new QuitCheck(), this);

        // bStats
        int pluginId = 15372;
        new Metrics(this, pluginId);
        //

        if (Config.getBool("check-update")) {
            new UpdateChecker(this, 102262).getVersion(version -> {
                if (this.getDescription().getVersion().equals(version)) {
                    getLogger().info(Locale.getMessage("update-check.latest"));
                } else {
                    getLogger().info(Locale.getMessage("update-check.outdate"));
                }
            });
        }

        getLogger().info("Plugin " + getConfig().getString("Version") + " loaded!");
    }

}
