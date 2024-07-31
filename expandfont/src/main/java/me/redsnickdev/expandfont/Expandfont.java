package me.redsnickdev.expandfont;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.chat.TextComponent;

public class Expandfont extends JavaPlugin {

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new FontPlaceholder(this).register();
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("expandfont")) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /expandfont <font> <placeholder>");
                return true;
            }
            String font = args[0];
            String placeholder = args[1];
            String text = PlaceholderAPI.setPlaceholders((Player) sender, "%" + placeholder + "%");

            TextComponent message = new TextComponent(text);
            message.setFont(font);
            ((Player) sender).spigot().sendMessage(message);
            return true;
        }
        return false;
    }
}