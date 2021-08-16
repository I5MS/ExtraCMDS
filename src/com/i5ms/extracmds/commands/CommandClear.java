package com.i5ms.extracmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandClear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if(sender.hasPermission("extracmds.clear.others")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        Clear(player, target);
                    }else {
                        player.sendMessage(ChatColor.RED + "I cannot find the player: " + args[0]);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
                }
            } else {
                Clear(player, player);
            }
        }
        return true;
    }

    public void Clear(Player sender, Player player) {
        if(sender.hasPermission("extracmds.clear")) {
            Inventory inv = player.getInventory();
            inv.clear();
            player.sendMessage(ChatColor.GOLD + "Your inventory has been cleared.");
            sender.sendMessage(ChatColor.GOLD + "The player " + player.getName() + " inventory has been cleared.");
            System.console().writer().println("[ExtraCMDS] The player " + player.getName() + " inventory has been cleared.");
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
        }
    }
}
