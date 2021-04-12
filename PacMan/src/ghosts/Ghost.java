package edu.ysu.csis.ghosts;

import edu.ysu.csis.Direction;
import edu.ysu.csis.Location;
import java.awt.Color;

public interface Ghost {
    public void move();
    public Color getColor();
    public Location getLocation();
    public void setLocation(Location location);
    public Integer getPoints();
    public Direction getDirection();
    public void setDirection(Direction direction);
    public Boolean canMove();
    public void reset();
}
