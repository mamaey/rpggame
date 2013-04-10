/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

import static rpggame.mapeditor.FigureBase.MAXLIVE;

/**
 *
 * @author Andre
 */




public class FigureBase {
    final static double MAXLIVE = 100.0;
    private int x,y;
    private double live;
    private FigureType figureType;
    
    public void FigureBase (int x, int y) {
        this.x = x;
        this.y = y;
        this.live = MAXLIVE;
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
    
    public double getLive() {
        return live;
    }
    
    public void setLive(double live) {
        this.live = live;
    }
       
    
    public FigureType getFigureType() {
        return figureType;
    }
    
    
    
}
