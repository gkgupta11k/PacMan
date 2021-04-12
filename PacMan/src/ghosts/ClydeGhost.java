package edu.ysu.csis.ghosts;

import edu.ysu.csis.Location;
import java.awt.Color;

public class ClydeGhost extends AbstractGhost {

    public ClydeGhost(Location location) {
        super(location, 100);
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }  
}
