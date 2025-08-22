package com.terocraft.crouch.listeners;

import com.terocraft.crouch.TeroCraftPlugin;
import com.terocraft.crouch.managers.ConfigManager;
import com.terocraft.crouch.managers.MessageHandler;
import com.terocraft.crouch.managers.PlayerDataManager;
import com.terocraft.crouch.models.PlayerCrouchData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerEventListener implements Listener {
    
    private final PlayerDataManager playerDataManager;
    private final MessageHandler messageHandler;
    private final ConfigManager configManager;
    
    public PlayerEventListener(TeroCraftPlugin plugin, PlayerDataManager playerDataManager, 
                             MessageHandler messageHandler, ConfigManager configManager) {
        this.playerDataManager = playerDataManager;
        this.messageHandler = messageHandler;
        this.configManager = configManager;
    }
    
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        
        // Check if plugin is enabled and player has permission
        if (!configManager.isPluginEnabled() || !player.hasPermission("terocraft.crouch.use")) {
            return;
        }
        
        // Only handle when player starts sneaking (not when they stop)
        if (!event.isSneaking()) {
            return;
        }
        
        PlayerCrouchData data = playerDataManager.getPlayerData(player.getUniqueId());
        long currentTime = System.currentTimeMillis();
        
        // Check if too much time has passed since last press
        if (currentTime - data.getLastPressTime() > configManager.getPressTimeout()) {
            data.resetPressCount();
        }
        
        // Increment press count and update timestamp
        data.incrementPressCount();
        data.setLastPressTime(currentTime);
        
        if (configManager.isDebugEnabled()) {
            player.sendMessage("§7Debug: Shift press " + data.getPressCount() + "/" + configManager.getShiftPresses());
        }
        
        // Check if player has reached the required number of presses
        if (data.getPressCount() >= configManager.getShiftPresses()) {
            if (!data.isCrouching()) {
                // Player enters crouch state
                data.setCrouching(true);
                data.resetPressCount();
                
                // Send crouch message
                messageHandler.sendCrouchMessage(player);
                
                // Play sound if enabled
                if (configManager.isSoundsEnabled()) {
                    messageHandler.playCrouchSound(player);
                }
                
                if (configManager.isDebugEnabled()) {
                    player.sendMessage("§7Debug: Entered crouch state");
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        
        // Check if plugin is enabled and player has permission
        if (!configManager.isPluginEnabled() || !player.hasPermission("terocraft.crouch.use")) {
            return;
        }
        
        PlayerCrouchData data = playerDataManager.getPlayerData(player.getUniqueId());
        
        // Only handle if player is currently in crouch state
        if (data.isCrouching()) {
            // Player exits crouch state
            data.setCrouching(false);
            data.resetPressCount();
            
            // Send stand message
            messageHandler.sendStandMessage(player);
            
            // Play sound if enabled
            if (configManager.isSoundsEnabled()) {
                messageHandler.playStandSound(player);
            }
            
            if (configManager.isDebugEnabled()) {
                player.sendMessage("§7Debug: Exited crouch state");
            }
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Clean up player data when they disconnect
        playerDataManager.removePlayerData(event.getPlayer().getUniqueId());
    }
}