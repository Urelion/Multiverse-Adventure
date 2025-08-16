package com.onarandombox.MultiverseAdventure.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import com.onarandombox.MultiverseAdventure.MultiverseAdventure;

public class LoadCommand extends BaseCommand {

    public LoadCommand(MultiverseAdventure plugin) {
        super(plugin);
        this.setName("Reload Adventure");
        this.setCommandUsage("/mva load " + ChatColor.GREEN + "[WORLD]");
        this.setArgRange(0, 1);
        this.addKey("mva load");
        this.addKey("mvaload");
        this.setPermission("multiverse.adventure.load", "Loads an adventure world.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        String world;
        if (args.isEmpty()) {
            if (sender instanceof Player) {
                world = ((Player) sender).getWorld().getName();
            }
            else {
                sender.sendMessage("If you want me to automatically recognize your world, you'd better be a player ;)");
                return;
            }
        }
        else {
            world = args.get(0);
        }
        
        
        if (plugin.getConfig().contains("adventure." + world)) {
            boolean enabled = plugin.getConfig().getBoolean("adventure." + world + ".enabled", false);
            if (!enabled) {
                sender.sendMessage("This world is no AdventureWorld!");
                
            } else {
                if (plugin.getCore().getMVWorldManager().loadWorld(world)) {
                    sender.sendMessage("AdventureWorld loaded!");
                } else {
                    sender.sendMessage("Failed load AdventureWorld!");
                }
            }
        } else {
            sender.sendMessage("Could not find this world!");
        }

    }

}
