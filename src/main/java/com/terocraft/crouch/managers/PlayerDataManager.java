package com.terocraft.crouch.managers;

import com.terocraft.crouch.models.PlayerCrouchData;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    
    private final Map<UUID, PlayerCrouchData> playerData;
    
    public PlayerDataManager() {
        this.playerData = new HashMap<>();
    }
    
    /**
     * Get player crouch data, creating new data if it doesn't exist
     */
    public PlayerCrouchData getPlayerData(UUID playerId) {
        return playerData.computeIfAbsent(playerId, k -> new PlayerCrouchData());
    }
    
    /**
     * Remove player data (used when player disconnects)
     */
    public void removePlayerData(UUID playerId) {
        playerData.remove(playerId);
    }
    
    /**
     * Check if player is currently in crouch state
     */
    public boolean isPlayerCrouching(UUID playerId) {
        PlayerCrouchData data = playerData.get(playerId);
        return data != null && data.isCrouching();
    }
    
    /**
     * Get the number of players currently crouching
     */
    public int getCrouchingPlayersCount() {
        return (int) playerData.values().stream()
                .filter(PlayerCrouchData::isCrouching)
                .count();
    }
    
    /**
     * Clean up all player data (used on plugin disable)
     */
    public void cleanup() {
        playerData.clear();
    }
    
    /**
     * Get total number of tracked players
     */
    public int getTrackedPlayersCount() {
        return playerData.size();
    }
}