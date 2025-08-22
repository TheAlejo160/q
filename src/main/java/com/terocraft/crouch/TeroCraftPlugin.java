package com.terocraft.crouch;

import com.terocraft.crouch.listeners.PlayerEventListener;
import com.terocraft.crouch.managers.ConfigManager;
import com.terocraft.crouch.managers.MessageHandler;
import com.terocraft.crouch.managers.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class TeroCraftPlugin extends JavaPlugin {
    
    private ConfigManager configManager;
    private MessageHandler messageHandler;
    private PlayerDataManager playerDataManager;
    private PlayerEventListener eventListener;
    
    @Override
    public void onEnable() {
        // Initialize managers
        this.configManager = new ConfigManager(this);
        this.messageHandler = new MessageHandler(this);
        this.playerDataManager = new PlayerDataManager();
        
        // Load configuration
        configManager.loadConfig();
        
        // Register event listener
        this.eventListener = new PlayerEventListener(this, playerDataManager, messageHandler, configManager);
        getServer().getPluginManager().registerEvents(eventListener, this);
        
        // Log startup message
        getLogger().info("TeroCraft Crouch Plugin has been enabled!");
        getLogger().info("Shift presses required: " + configManager.getShiftPresses());
    }
    
    @Override
    public void onDisable() {
        // Clean up player data
        if (playerDataManager != null) {
            playerDataManager.cleanup();
        }
        
        getLogger().info("TeroCraft Crouch Plugin has been disabled!");
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("terocrouchreload")) {
            if (!sender.hasPermission("terocraft.crouch.reload")) {
                sender.sendMessage("§cYou don't have permission to use this command!");
                return true;
            }
            
            // Reload configuration
            configManager.loadConfig();
            sender.sendMessage("§aTeroCraft Crouch Plugin configuration reloaded!");
            return true;
        }
        
        return false;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
    
    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}