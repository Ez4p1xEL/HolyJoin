package p1xel.holyjoin.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import p1xel.holyjoin.Storage.GroupSettings;

public class QuitCheck implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        String name = p.getName();

        for (String key : GroupSettings.get().getStringList("priority")) {
            if (p.hasPermission("holyjoin." + key)) {

                if (GroupSettings.isQuitExist(key)) {

                    if (!GroupSettings.getQuitDefaultSetting(key)) {

                        for (String m : GroupSettings.getQuitMessage(key)) {
                            Bukkit.broadcastMessage(GroupSettings.translate(PlaceholderAPI.setPlaceholders(p, m.replaceAll("%player%", name))));
                        }

                        if (!GroupSettings.getQuitDefaultSetting(key)) {
                            e.setQuitMessage(null);
                        }

                    }
                }

                break;

            }
        }

    }

}
