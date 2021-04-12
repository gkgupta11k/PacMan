package edu.ysu.csis;

import edu.ysu.csis.food.Food;
import edu.ysu.csis.food.FoodImpl;
import edu.ysu.csis.food.FoodType;
import edu.ysu.csis.ghosts.BlinkyGhost;
import edu.ysu.csis.ghosts.ClydeGhost;
import edu.ysu.csis.ghosts.Ghost;
import edu.ysu.csis.ghosts.InkyGhost;
import edu.ysu.csis.ghosts.PinkyGhost;
import edu.ysu.csis.map.GameMap;
import edu.ysu.csis.map.SpaceType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePanel extends javax.swing.JPanel implements ActionListener {
    private PacMan pacMan;
    private final GameMap gameMap;
    private final SpaceType[][] level;
    private final Map<String, Food> foodMap;
    private final List<Location> wallList;
    private final List<Ghost> ghostList;
    
    private Integer superFoodDuration;
    private Integer count;
    private Location ghostStartLocation;
    
    public GamePanel() {
        initComponents();
        gameMap = new GameMap();
        foodMap = new HashMap();
        wallList = new ArrayList();
        ghostList = new ArrayList();
        level = gameMap.getNextLevel();
        superFoodDuration = 0;
        count = 0;
        
        
        initializeGame();
    }
    
    private void initializeGame() {
        for(int i = 0; i < GameSettings.Y_COUNT; i++) {
            for(int j = 0; j < GameSettings.X_COUNT; j++) {
                Location location = new Location(j * GameSettings.SCALE, i * GameSettings.SCALE);
                if(level[i][j] != null) {
                    switch(level[i][j]) {
                        case Food:
                        case Fruit:
                        case SuperFood:
                            initializeFood(j, i, level[i][j]);
                            break;
                        case Wall:
                            wallList.add(location);
                            break;
                        case Pinky:
                            ghostList.add(new PinkyGhost(location));
                            break;
                        case Clyde:
                            ghostList.add(new ClydeGhost(location));
                            break;
                        case Blinky:
                            Ghost ghost = new BlinkyGhost(location);
                            ghost.setDirection(Direction.Right);
                            ghostStartLocation = ghost.getLocation().clone();
                            ghostList.add(ghost);
                            break;
                        case Inky:
                            ghostList.add(new InkyGhost(location));
                            break;
                        case PacMan:
                            pacMan = new PacMan(location);
                            break;
                    }
                }
            }
        }
    }
    
    private void initializeFood(Integer x, Integer y, SpaceType spaceType) {
        FoodType foodType;
        Integer points;
        
        switch(spaceType) {
            case Fruit:
                foodType = FoodType.Fruit;
                points = 75;
                break;
            case SuperFood:
                foodType = FoodType.SuperFood;
                points = 100;
                break;
            case Food:
            default:
                foodType = FoodType.Food;
                points = 25;
        }
        
        Location location = new Location(x * GameSettings.SCALE, y * GameSettings.SCALE);
        Food food = new FoodImpl(points, location, foodType);
        foodMap.put(location.toString(), food);
    }
    
    public void start() {
        GameSettings gameSettings = GameSettings.getInstance();
        Timer timer = new Timer(gameSettings.getGameSpeed(), this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.fill3DRect(30, 30, 30, 30, true);
        
        drawWalls(graphics);
        drawFood(graphics);
        drawGhosts(graphics);
        drawPacMan(graphics);
    }
    
    private void drawWalls(Graphics graphics) {
        graphics.setColor(GameSettings.WALL_COLOR);
        
        for(Location location: wallList) {
            graphics.fillRect(location.getX(), location.getY(), GameSettings.SCALE, GameSettings.SCALE);
        }       
    }
    
    private void drawFood(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        
//        for(int i = 0; i < foodList.size(); i++) {
//            Food food = foodList.get(i) 
//        }
//        for(Food food: foodList)
        foodMap.values().forEach((food) -> {
            if(food.getFoodType() == FoodType.SuperFood) {
                drawSuperFood(graphics, food);
            } else {
                Location location = food.getLocation();
                graphics.fillOval(
                        location.getX() + GameSettings.SCALE / 3,
                        location.getY() + GameSettings.SCALE / 3,
                        10, 10
                );
            }
        });
    }
    
    private void drawSuperFood(Graphics graphics, Food food) {
        graphics.setColor(Color.WHITE);
        Location location = food.getLocation();
        graphics.fillOval(
            location.getX() + GameSettings.SCALE / 6, 
            location.getY() + GameSettings.SCALE / 6, 
            20, 20
        );
    }
    
    private void drawGhosts(Graphics graphics) {
        for(Ghost ghost: ghostList) {
            Color color = ghost.getColor();
            
            if(this.superFoodDuration > 0) {
                color = GameSettings.SUPER_FOOD_GHOST_COLOR;
            }
            
            graphics.setColor(color);
            Location location = ghost.getLocation();
            graphics.fillRect(location.getX(), location.getY(), GameSettings.SCALE, GameSettings.SCALE);
        }
    }
    
    private void drawPacMan(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        Location location = pacMan.getLocation();
        Direction direction = pacMan.getDirection();

        Integer startAngle = pacMan.isMouthOpen() ? 40 : 20;
        Integer arcAngle = pacMan.isMouthOpen() ? 280 : 320;
        
        if(direction != null) {
            switch(direction) {
                case Up:
                    startAngle = 90 + startAngle;
                    break;
                case Left:
                    startAngle = 180 + startAngle;
                    break;
                case Down:
                    startAngle = 270 + startAngle;
                    break;
                case Right:
                default:
                    startAngle = 0 + startAngle;
                    break;
            }
        }
        
        graphics.fillArc(
            location.getX(), location.getY(), 
            GameSettings.SCALE, GameSettings.SCALE, 
            startAngle, arcAngle
        );
    }
    
    public void setPacManDirection(Direction direction) {
        Location location = pacMan.getLocation();
        SpaceType spaceType = this.getNextSpaceType(location, direction);
        if(spaceType != SpaceType.Wall) {
            this.pacMan.setDirection(direction);
        }
    }
    
    private Integer getIndexX(Location location) {
        return location.getX() / GameSettings.SCALE;
    }
    
    private Integer getIndexY(Location location) {
        return location.getY() / GameSettings.SCALE;
    }
    
    private void setGhostsDirections() {
        this.ghostList.forEach((ghost) -> {
            if(ghost instanceof BlinkyGhost) {
                ghost.setDirection(getRandomDirection(ghost));
            }
            if(count >= GameSettings.GHOST_SPAWN_DURATION) {
                if(ghost instanceof PinkyGhost) {
                    if(count.equals(GameSettings.GHOST_SPAWN_DURATION)) {
                        ghost.setLocation(this.ghostStartLocation);
                        ghost.setDirection(Direction.Right);
                    } else {
                        ghost.setDirection(getRandomDirection(ghost));
                    }
                }
            }
            if(count >= GameSettings.GHOST_SPAWN_DURATION * 2) {
                if(ghost instanceof ClydeGhost) {
                    if(count.equals(GameSettings.GHOST_SPAWN_DURATION * 2)) {
                        ghost.setLocation(this.ghostStartLocation);
                        ghost.setDirection(Direction.Right);
                    } else {
                        ghost.setDirection(getRandomDirection(ghost));
                    }
                }
            }
            
            if(count >= GameSettings.GHOST_SPAWN_DURATION * 3) {
                if(ghost instanceof InkyGhost) {
                    if(count.equals(GameSettings.GHOST_SPAWN_DURATION * 3)) {
                        ghost.setLocation(this.ghostStartLocation);
                        ghost.setDirection(Direction.Right);
                    } else {
                        ghost.setDirection(getRandomDirection(ghost));
                    }
                }
            }
        });
    }
    
    private Direction getRandomDirection(Ghost ghost) {
        List<Direction> directionList = new ArrayList();
        
        Location location = ghost.getLocation();
        for(Direction direction: Direction.values()) {
            SpaceType spaceType = this.getNextSpaceType(location, direction);
            if(spaceType != SpaceType.Wall && !isOppositeDirection(direction, ghost.getDirection())) {
                directionList.add(direction);
            }
        }
        
        return directionList.get(new Random().nextInt(directionList.size()));
    }
    
    private Boolean isOppositeDirection(Direction directionOne, Direction directionTwo) {
        Boolean isOppositeDirection = false;
        
        switch(directionOne) {
            case Up:
                isOppositeDirection = directionTwo == Direction.Down;
                break;
            case Down:
                isOppositeDirection = directionTwo == Direction.Up;
                break;
            case Right:
                isOppositeDirection = directionTwo == Direction.Left;
                break;
            case Left:
                isOppositeDirection = directionTwo == Direction.Right;
                break;
        }
        
        return isOppositeDirection;
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        count++;
        
        if(this.superFoodDuration > 0) {
            this.superFoodDuration--;
        }
        
        setGhostsDirections();
        
        if(canPacManMove()) {
            this.pacMan.move();
            checkForFood();
        }
        
        checkForGhosts();
        
        this.ghostList.forEach((ghost) -> {
            if(ghost.canMove()) {
                ghost.move();
            }
        });
        
        this.validate();
        this.repaint();
    }
    
    private void checkForGhosts() {
        Location location = this.pacMan.getLocation();
        
        this.ghostList.forEach((ghost) -> {
            if(ghost.getLocation().equals(location)) {
                if(this.superFoodDuration > 0) {
                    ghost.reset();
                } else {
                    //Pacman loses a life
                    //Reset Pacman position
                    //Reset Ghosts postions
                    //If pacman has no more life, rest the game
                }
            }
        });
    }
    
    private void checkForFood() {
        Location location = this.pacMan.getLocation();
        Food food = foodMap.remove(location.toString());
        if(food != null) {
            if(food.getFoodType() == FoodType.SuperFood) {
                activateSuperPacMan();
            }
            
            MainFrame mainFrame = (MainFrame)SwingUtilities.getRoot(this);
            pacMan.appendPoints(food.getPoints());
            mainFrame.updatePoints(pacMan.getPoints());
        }
    }
    
    private void activateSuperPacMan() {
        this.superFoodDuration = GameSettings.SUPER_FOOD_DURATION;
    }
    
    private Boolean canPacManMove() {
        Boolean canMove = false;
        
        Direction direction = this.pacMan.getDirection();
        
        if(direction != null) {
            Location location = this.pacMan.getLocation();
            SpaceType spaceType = getNextSpaceType(location, direction);
            if(spaceType != SpaceType.Wall) {
                canMove = true;
            }
        }
        
        return canMove;
    }
    
    private SpaceType getNextSpaceType(Location location, Direction direction) {
        SpaceType spaceType = null;
        
        Integer j = this.getIndexX(location);
        Integer i = this.getIndexY(location);
                                
        if(j >= 0 && j < (GameSettings.X_COUNT) && i >= 0 && i < (GameSettings.Y_COUNT)) {
            switch(direction) {
                case Up:
                    spaceType = level[getNextIndex(i, GameSettings.Y_COUNT-1, false)][j];
                    break;
                case Down:
                    spaceType = level[i==GameSettings.Y_COUNT-1?0:i+1][j];
                    break;
                case Left:
                    spaceType = level[i][j==0?GameSettings.X_COUNT-1:j-1];
                    break;
                case Right:
                    spaceType = level[i][getNextIndex(j, GameSettings.X_COUNT-1, true)];
                    break;
            }
        }
        
        return spaceType;
    }
    
    private Integer getNextIndex(Integer index, Integer maxIndex, Boolean isPositive) {
        Integer nextIndex = 0;
        
        if(index == 0) {
            nextIndex = maxIndex;
        } else if(index.equals(maxIndex)) {
            nextIndex = 0;
        } else {
            if(isPositive) {
                nextIndex = index + 1;
            } else {
                nextIndex = index - 1;
            }
        }
        
        return nextIndex;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(930, 930));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
