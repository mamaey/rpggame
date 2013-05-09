/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;


/**
 *
 * @author Andre
 */




public class FigureBase {
    final static double MAXHEALTH = 100.0;
    private int x,y;
    private double health;
    private FigureType figureType;
    
    public FigureBase (int x, int y) {
        this.x = x;
        this.y = y;
        this.health = MAXHEALTH;
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
