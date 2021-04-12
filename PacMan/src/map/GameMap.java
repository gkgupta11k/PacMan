package edu.ysu.csis.map;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private static final SpaceType PM   = SpaceType.PacMan;
    private static final SpaceType FD   = SpaceType.Food;
    private static final SpaceType SF   = SpaceType.SuperFood;
    private static final SpaceType BG   = SpaceType.Blinky;
    private static final SpaceType PG   = SpaceType.Pinky;
    private static final SpaceType IG   = SpaceType.Inky;
    private static final SpaceType CG   = SpaceType.Clyde;
    private static final SpaceType WL   = SpaceType.Wall;
    private static final SpaceType NU   = null;
    
    private List<SpaceType[][]> levels;
    private Integer currentLevel = 0;
    
    public GameMap() {
        this.levels = new ArrayList();
        this.levels.add(getLevelOne());
    }
    
    public SpaceType[][] getNextLevel() {
        return this.levels.get(currentLevel++);
    }
    
    private static SpaceType[][] getLevelOne() {
        SpaceType[][] level = 
        { 
            {WL,WL,NU,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL},
            {WL,SF,FD,FD,FD,FD,FD,FD,WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL,FD,FD,FD,FD,FD,FD,SF,WL},
            {WL,FD,WL,WL,WL,WL,WL,FD,WL,FD,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,FD,WL,FD,WL,WL,WL,WL,WL,FD,WL},
            {WL,FD,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,FD,WL},
            {NU,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL,FD,WL,WL,FD,WL,FD,WL,WL,FD,WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,NU},
            {WL,FD,WL,WL,WL,WL,FD,WL,WL,WL,WL,FD,WL,WL,FD,WL,FD,WL,WL,FD,WL,WL,WL,WL,FD,WL,WL,WL,WL,FD,WL},
            {WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL,WL,FD,WL,FD,WL,WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL},
            {WL,FD,WL,WL,WL,WL,FD,WL,WL,FD,WL,FD,FD,FD,FD,WL,FD,FD,FD,FD,WL,FD,WL,WL,FD,WL,WL,WL,WL,FD,WL},
            {WL,FD,WL,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,WL,FD,WL},
            {WL,FD,WL,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,WL,FD,WL},
            {WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,BG,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL},
            {WL,FD,WL,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,WL,FD,WL},
            {WL,FD,WL,FD,SF,FD,FD,WL,FD,WL,FD,WL,FD,WL,IG,PG,CG,WL,FD,WL,FD,WL,FD,WL,FD,FD,SF,FD,WL,FD,WL},
            {NU,FD,FD,FD,WL,WL,FD,FD,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,FD,FD,WL,WL,FD,FD,FD,NU},
            {WL,FD,WL,FD,FD,FD,FD,WL,FD,WL,FD,WL,FD,FD,FD,FD,FD,FD,FD,WL,FD,WL,FD,WL,FD,FD,FD,FD,WL,FD,WL},
            {WL,FD,WL,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,FD,WL,FD,WL,FD,WL,WL,WL,WL,WL,WL,FD,WL},
            {WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL,FD,FD,FD,WL,FD,FD,FD,WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL},
            {WL,FD,WL,FD,WL,WL,WL,FD,WL,WL,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,WL,WL,FD,WL,WL,WL,FD,WL,FD,WL},
            {WL,FD,WL,FD,WL,WL,WL,FD,WL,FD,FD,FD,FD,WL,FD,WL,FD,WL,FD,FD,FD,FD,WL,FD,WL,WL,WL,FD,WL,FD,WL},
            {WL,FD,WL,FD,FD,FD,FD,FD,WL,FD,WL,WL,WL,WL,FD,WL,FD,WL,WL,WL,WL,FD,WL,FD,FD,FD,FD,FD,WL,FD,WL},
            {WL,FD,WL,FD,WL,WL,WL,FD,FD,FD,FD,FD,FD,FD,FD,WL,FD,FD,FD,FD,FD,FD,FD,FD,WL,WL,WL,FD,WL,FD,WL},
            {WL,FD,FD,FD,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,FD,WL,FD,WL,WL,WL,FD,WL,WL,FD,WL,WL,WL,FD,FD,FD,WL},
            {WL,WL,WL,FD,WL,WL,WL,FD,WL,WL,FD,WL,FD,FD,FD,PM,FD,FD,FD,WL,FD,WL,WL,FD,WL,WL,WL,FD,WL,WL,WL},
            {NU,FD,FD,FD,FD,FD,FD,FD,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,FD,FD,FD,FD,FD,FD,FD,NU},
            {WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL},
            {WL,FD,WL,FD,WL,FD,FD,FD,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,FD,FD,FD,WL,FD,WL,FD,WL},
            {WL,FD,WL,FD,WL,WL,WL,FD,WL,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,FD,WL,WL,FD,WL,WL,WL,FD,WL,FD,WL},
            {WL,FD,WL,SF,FD,FD,FD,FD,FD,FD,FD,WL,FD,WL,FD,FD,FD,WL,FD,WL,FD,FD,FD,FD,FD,FD,FD,SF,WL,FD,WL},
            {WL,FD,WL,WL,WL,WL,WL,FD,WL,WL,WL,WL,FD,WL,WL,WL,WL,WL,FD,WL,WL,WL,WL,FD,WL,WL,WL,WL,WL,FD,WL},
            {WL,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,FD,WL},
            {WL,WL,NU,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL,WL}
        };
        
        return level;
    }
}
