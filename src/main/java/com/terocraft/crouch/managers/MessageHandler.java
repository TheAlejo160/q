package com.terocraft.crouch.managers;

import com.terocraft.crouch.TeroCraftPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageHandler {
    
    private final TeroCraftPlugin plugin;
    private final ConfigManager configManager;
    
    public MessageHandler(TeroCraftPlugin plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
    }
    
    /**
     * Send the crouch message to a player
     */
    public void sendCrouchMessage(Player player) {
        String message = configManager.getCrouchMessage();
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        player.sendMessage(component);
    }
    
    /**
     * Send the stand message to a player
     */
    public void sendStandMessage(Player player) {
        String message = configManager.getStandMessage();
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        player.sendMessage(component);
    }
    
    /**
     * Play crouch sound to a player
     */
    public void playCrouchSound(Player player) {
        try {
            String soundName = configManager.getCrouchSound();
            Sound sound = Sound.valueOf(soundName);
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid crouch sound: " + configManager.getCrouchSound());
        }
    }
    
    /**
     * Play stand sound to a player
     */
    public void playStandSound(Player player) {
        try {
            String soundName = configManager.getStandSound();
            Sound sound = Sound.valueOf(soundName);
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid stand sound: " + configManager.getStandSound());
        }
    }
    
    /**
     * Send a formatted message to a player
     */
    public void sendFormattedMessage(Player player, String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        player.sendMessage(component);
    }
    
    /**
     * Broadcast a formatted message to all online players
     */
    public void broadcastMessage(String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        plugin.getServer().broadcast(component);
    }
}