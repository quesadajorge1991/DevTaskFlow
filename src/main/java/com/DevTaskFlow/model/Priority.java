package com.DevTaskFlow.model;

public enum Priority {
    LOW("Baja"),
    MEDIUM("Media"),
    HIGH("Alta");
    
    private final String displayName;
    
    Priority(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

}
