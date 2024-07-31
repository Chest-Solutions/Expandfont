package me.redsnickdev.expandfont;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FontPlaceholder extends PlaceholderExpansion {

    private final Expandfont plugin;

    public FontPlaceholder(Expandfont plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "expandfont";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        // Check if the parameters match the format: MyFont_{PLACEHOLDER}
        int underscoreIndex = params.indexOf('_');
        if (underscoreIndex != -1) {
            String fontName = params.substring(0, underscoreIndex); // Get the font name
            String placeholder = params.substring(underscoreIndex + 1); // Get the placeholder part

            // Get the actual value for the placeholder
            String text = PlaceholderAPI.setPlaceholders(player, "%" + placeholder + "%");

            if (text != null) {
                // Create a TextComponent for the text
                TextComponent message = new TextComponent(text);
                // Set the font to the TextComponent
                message.setFont(fontName); // This sets the font

                // Send the message to the player
                player.spigot().sendMessage(message);

                return ""; // No return value needed since the message is sent directly
            }
        }

        return null; // Return null if it doesn't match
    }
}
