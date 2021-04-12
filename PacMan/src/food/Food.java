package edu.ysu.csis.food;

import edu.ysu.csis.Location;
import java.awt.Color;

public interface Food {
    public Integer getPoints();
    public Location getLocation();
    public FoodType getFoodType();
    public Color getColor();
}
