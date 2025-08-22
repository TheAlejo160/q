package com.terocraft.crouch.managers;

import com.terocraft.crouch.TeroCraftPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    
    private final TeroCraftPlugin plugin;
    private FileConfiguration config;
    
    // Default values
    private static final boolean DEFAULT_PLUGIN_ENABLED = true;
    private static final int DEFAULT_SHIFT_PRESSES = 5;
    private static final long DEFAULT_PRESS_TIMEOUT = 2000L;
    private static final boolean DEFAULT_DEBUG = false;
    private static final String DEFAULT_CROUCH_MESSAGE = "&7&l[&c&lTeroCraft&7&l] &fTe estas agachando";
    private static final String DEFAULT_STAND_MESSAGE = "&7&l[&c&lTeroCraft&7&l] &fTe has levantado";
    private static final String DEFAULT_CROUCH_SOUND = "BLOCK_NOTE_BLOCK_BASS";
    private static final String DEFAULT_STAND_SOUND = "BLOCK_NOTE_BLOCK_BELL";
    private static final boolean DEFAULT_SOUNDS_ENABLED = true;
    
    public ConfigManager(TeroCraftPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void loadConfig() {
        // Save default config if it doesn't exist
        plugin.saveDefaultConfig();
        
        // Reload config from file
        plugin.reloadConfig();
        this.config = plugin.getConfig();
        
        // Validate configuration values
        validateConfig();
        
        plugin.getLogger().info("Configuration loaded successfully!");
    }
    
    private void validateConfig() {
        // Validate shift presses (must be between 1 and 20)
        int shiftPresses = config.getInt("crouch.shift-presses", DEFAULT_SHIFT_PRESSES);
        if (shiftPresses < 1 || shiftPresses > 20) {
            plugin.getLogger().warning("Invalid shift-presses value: " + shiftPresses + ". Using default: " + DEFAULT_SHIFT_PRESSES);
            config.set("crouch.shift-presses", DEFAULT_SHIFT_PRESSES);
        }
        
        // Validate press timeout (must be between 500ms and 10000ms)
        long pressTimeout = config.getLong("crouch.press-timeout", DEFAULT_PRESS_TIMEOUT);
        if (pressTimeout < 500 || pressTimeout > 10000) {
            plugin.getLogger().warning("Invalid press-timeout value: " + pressTimeout + ". Using default: " + DEFAULT_PRESS_TIMEOUT);
            config.set("crouch.press-timeout", DEFAULT_PRESS_TIMEOUT);
        }
    }
    
    // Plugin settings
    public boolean isPluginEnabled() {
        return config.getBoolean("plugin.enabled", DEFAULT_PLUGIN_ENABLED);
    }
    
    // Crouch settings
    public int getShiftPresses() {
        return config.getInt("crouch.shift-presses", DEFAULT_SHIFT_PRESSES);
    }
    
    public long getPressTimeout() {
        return config.getLong("crouch.press-timeout", DEFAULT_PRESS_TIMEOUT);
    }
    
    public boolean isDebugEnabled() {
        return config.getBoolean("crouch.debug", DEFAULT_DEBUG);
    }
    
    // Message settings
    public String getCrouchMessage() {
        return config.getString("messages.crouch-message", DEFAULT_CROUCH_MESSAGE);
    }
    
    public String getStandMessage() {
        return config.getString("messages.stand-message", DEFAULT_STAND_MESSAGE);
    }
    
    // Sound settings
    public String getCrouchSound() {
        return config.getString("sounds.crouch-sound", DEFAULT_CROUCH_SOUND);
    }
    
    public String getStandSound() {
        return config.getString("sounds.stand-sound", DEFAULT_STAND_SOUND);
    }
    
    public boolean isSoundsEnabled() {
        return config.getBoolean("sounds.enabled", DEFAULT_SOUNDS_ENABLED);
    }
}