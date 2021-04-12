package edu.ysu.csis;

import java.awt.Color;

public class GameSettings {
    private final static GameSettings INSTANCE = new GameSettings();
    
    public static final Integer X_COUNT = 31;
    public static final Integer Y_COUNT = 31;
    public static final Integer SCALE = 30;
    public static final Integer GAME_WIDTH = X_COUNT * SCALE;
    public static final Integer GAME_HEIGHT = Y_COUNT * SCALE;
    public static final Integer GHOST_RESPAWN_DURATION = 30;
    public static final Integer GHOST_SPAWN_DURATION = 30;
    public static final Integer SUPER_FOOD_DURATION = 30;
    public static final Color SUPER_FOOD_GHOST_COLOR = Color.LIGHT_GRAY;
    public static final Color WALL_COLOR = Color.RED;
    
    private Integer gameSpeed = 200;
    
    private GameSettings() {
        
    }
    
    public static GameSettings getInstance() {
        return INSTANCE;
    }

    public Integer getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(Integer gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
}
