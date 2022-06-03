package p1xel.holyjoin.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import p1xel.holyjoin.Storage.GroupSettings;

public class JoinCheck implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        Player p = e.getPlayer();
        String name = p.getName();

        for (String key : GroupSettings.get().getKeys(false)) {
            if (p.hasPermission("holyjoin." + key)) {

                if (GroupSettings.isJoinExist(key)) {

                    for (String m : GroupSettings.getJoinMessage(key)) {
                        Bukkit.broadcastMessage(GroupSettings.translate(PlaceholderAPI.setPlaceholders(p, m.replaceAll("%player%", name))));
                    }

                }

            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        String name = p.getName();

        for (String key : GroupSettings.get().getKeys(false)) {
            if (p.hasPermission("holyjoin." + key)) {

                if (GroupSettings.isJoinExist(key)) {

                    if (!GroupSettings.getJoinDefaultSetting(key)) {
                        e.setJoinMessage(null);
                    }

                }

                if (GroupSettings.isActionExist(key)) {

                    for (String actionkey : GroupSettings.get().getConfigurationSection(key + ".action").getKeys(false)) {

                        if (GroupSettings.getActionType(key, actionkey).equalsIgnoreCase("command")) {
                            String cmd = GroupSettings.getActionValue(key, actionkey, "command");
                            cmd = cmd.replaceAll("%player%", name);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                        }

                        if (GroupSettings.getActionType(key, actionkey).equalsIgnoreCase("message")) {
                            String m = GroupSettings.getActionValue(key,actionkey,"message");
                            m = PlaceholderAPI.setPlaceholders(p, m);
                            p.sendMessage(GroupSettings.translate(m));
                        }

                        if (GroupSettings.getActionType(key, actionkey).equalsIgnoreCase("sound")) {
                            p.playSound(p.getLocation(), Sound.valueOf(GroupSettings.getActionValue(key,actionkey,"sound")), 1.5f, 1.5f);
                        }

                    }

                }

            }
        }
    }

}
