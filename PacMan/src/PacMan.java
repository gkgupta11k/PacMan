package edu.ysu.csis;

public class PacMan {
    private Location location;
    private Direction direction;
    private Boolean mouthOpen;
    private Integer points;
    
    public PacMan(Location location) {
        this.location = location;
        this.mouthOpen = false;
        this.points = 0;
    }

    public Integer getPoints() {
        return points;
    }
    
    public void appendPoints(Integer points) {
        this.points += points;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public Boolean isMouthOpen() {
        return this.mouthOpen;
    }
    
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
            this.mouthOpen = !this.mouthOpen;
        }
    }
}
