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
        //super(x, y); ?? why 
        this.FigureBase(x, y);
    }
    
    public void move(int x, int y) { //teleport
        setXY(x,y);
    }
                
    
    public void move(int direction) { 
        int x, y;
        x = getX();
        y = getY();
        switch (direction) {
            case 0 : x += 1; break; //right
            case 1 : x -= 1; break; //left
            case 2 : y += 1; break; //down
            case 3 : y -= 1; break; //up
        }
        setXY(x,y);
    }
    
    public void changeHealth(double value) {
        setHealth(getHealth() + value);
    }
}
