package com.terocraft.crouch.models;

public class PlayerCrouchData {
    
    private int pressCount;
    private long lastPressTime;
    private boolean isCrouching;
    
    public PlayerCrouchData() {
        this.pressCount = 0;
        this.lastPressTime = 0L;
        this.isCrouching = false;
    }
    
    // Press count methods
    public int getPressCount() {
        return pressCount;
    }
    
    public void incrementPressCount() {
        this.pressCount++;
    }
    
    public void resetPressCount() {
        this.pressCount = 0;
    }
    
    // Timestamp methods
    public long getLastPressTime() {
        return lastPressTime;
    }
    
    public void setLastPressTime(long lastPressTime) {
        this.lastPressTime = lastPressTime;
    }
    
    // Crouch state methods
    public boolean isCrouching() {
        return isCrouching;
    }
    
    public void setCrouching(boolean crouching) {
        this.isCrouching = crouching;
    }
    
    // Utility methods
    public void reset() {
        this.pressCount = 0;
        this.lastPressTime = 0L;
        this.isCrouching = false;
    }
    
    @Override
    public String toString() {
        return "PlayerCrouchData{" +
                "pressCount=" + pressCount +
                ", lastPressTime=" + lastPressTime +
                ", isCrouching=" + isCrouching +
                '}';
    }
}