package edu.ysu.csis.ghosts;

import edu.ysu.csis.Direction;
import edu.ysu.csis.GameSettings;
import edu.ysu.csis.Location;

abstract class AbstractGhost implements Ghost {
    private Location location;
    private Location startLocation;
    private Integer points;
    private Direction direction;
    private Integer sleepCount = 0;
    
    public AbstractGhost(Location location, Integer points) {
        this.location = location;
        this.startLocation = location.clone();
        this.points = points;
    }
    
    @Override
    public void move() {
        if(this.direction != null) {
            Integer x = this.location.getX();
            Integer y = this.location.getY();
            
            switch(this.direction) {
                case Up: 
                    if(y > 0) {
                        y -= GameSettings.SCALE;
                    } else {
                        y = (GameSettings.Y_COUNT - 1) * GameSettings.SCALE;
                    }
                    break;
                case Down:
                    if(y < (GameSettings.Y_COUNT - 1) * GameSettings.SCALE) {
                        y += GameSettings.SCALE;
                    } else {
                        y = 0;
                    }
                    break;
                case Left:
                    if(x > 0) {
                        x -= GameSettings.SCALE;
                    } else {
                        x = (GameSettings.X_COUNT - 1) * GameSettings.SCALE;
                    }
                    
                    break;
                case Right:
                    if(x < (GameSettings.X_COUNT - 1) * GameSettings.SCALE) {
                        x += GameSettings.SCALE;
                    } else {
                        x = 0;
                    }
                    break;
            }
            
            this.location.setX(x);
            this.location.setY(y);
        }
    }
    
    @Override
    public Location getLocation() {
        return this.location;
    }
    
    @Override
    public void setLocation(Location location) {
        this.location = location.clone();
    }
    
    @Override
    public Integer getPoints() {
        return this.points;
    }
    
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }
    
    @Override
    public void reset() {
        this.location = this.startLocation;
        this.sleepCount = GameSettings.GHOST_RESPAWN_DURATION;
        this.direction = null;
    }
    
    @Override
    public Boolean canMove() {
        Boolean canMove = true;
        if(this.sleepCount > 0) {
            this.sleepCount--;
            canMove = false;
        }
        return canMove;
    }
}
