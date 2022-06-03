package p1xel.holyjoin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import p1xel.holyjoin.HolyJoin;
import p1xel.holyjoin.Storage.Config;
import p1xel.holyjoin.Storage.Locale;

public class Cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(Locale.translate("&b&lHolyJoin " + Config.getVersion()));
            sender.sendMessage(Locale.translate("&b&lmade by p1xEL_mc"));
            sender.sendMessage(Locale.getMessage("commands.help"));
            return true;

        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("help")) {

                if (!sender.hasPermission("holyjoin.admin")) {
                    sender.sendMessage(Locale.getMessage("no-perm"));
                    return true;
                }

                sender.sendMessage(Locale.getMessage("commands.top"));
                sender.sendMessage(Locale.getMessage("commands.plugin").replaceAll("%version%", Config.getVersion()));
                sender.sendMessage(Locale.getMessage("commands.space-1"));
                sender.sendMessage(Locale.getMessage("commands.help"));
                sender.sendMessage(Locale.getMessage("commands.reload"));
                sender.sendMessage(Locale.getMessage("commands.space-2"));
                sender.sendMessage(Locale.getMessage("commands.bottom"));
                return true;

            }

            if (args[0].equalsIgnoreCase("reload")) {

                if (!sender.hasPermission("holyjoin.admin")) {
                    sender.sendMessage(Locale.getMessage("no-perm"));
                    return true;
                }

                HolyJoin.getInstance().reloadConfig();
                sender.sendMessage(Locale.getMessage("reload-success"));
                return true;

            }

        }










        return false;
    }

}
