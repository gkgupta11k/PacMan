package edu.ysu.csis;

public class Location {
    private Integer x;
    private Integer y;

    public Location(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        
        if(object instanceof Location) {
            Location location = (Location)object;
            isEqual = this.getX().equals(location.getX()) && this.getY().equals(location.getY());
        }
        
        return isEqual;
    }
    
    @Override
    public String toString() {
        return x + " " + y;
    }
    
    @Override
    public Location clone() {
        return new Location(x, y);
    }
}
