/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;


/**
 *
 * @author Andre
 */




public class FigureBase {
    final static double MAXHEALTH = 100.0;
    private int x,y;
    private double health;
    private FigureType figureType;
    private Map map;
    
    public FigureBase (Map map, int x, int y) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.health = MAXHEALTH;
    }
    public FigureBase () {
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double getHealth() {
        return health;
    }
    
    public void setHealth(double health) {
        this.health = health;
    }
       
    
    public FigureType getFigureType() {
        return figureType;
    }
    
    
    
}
