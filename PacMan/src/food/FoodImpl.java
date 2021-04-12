package edu.ysu.csis.food;

import edu.ysu.csis.Location;
import java.awt.Color;

public class FoodImpl implements Food {
    private Integer points;
    private Location location;
    private FoodType foodType;
    
    public FoodImpl(Integer points, Location location, FoodType foodType) {
        this.points = points;
        this.location = location;
        this.foodType = foodType;
    }
    
    @Override
    public Integer getPoints() {
        return this.points;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public FoodType getFoodType() {
        return this.foodType;
    }

    @Override
    public Color getColor() {
        Color color;
        
        switch(this.foodType) {
            case Fruit:
                color = Color.RED;
                break;
            case SuperFood:
                color = Color.BLUE;
                break;
            case Food:
            default:
                color = Color.WHITE;
        }
        
        return color;
    }
}
