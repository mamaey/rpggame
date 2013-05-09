/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

/**
 *
 * @author Andre
 */
public class Figure extends FigureBase {
    public void Figure(int x, int y) {
        super (int x, int y);
    }
    
    public move(int x, int y) { //teleport
        setXY(x,y);        
    }
    
    public move(int direction) { 
        
    }
}
