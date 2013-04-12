/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import javax.xml.bind.annotation.XmlRootElement;
import rpggame.models.DirectionType;


/**
 *
 * @author Andre
 */
public class Figure extends FigureBase {
    public Figure(Map map,int x, int y) {
        super(map, x, y);
    }
    public Figure(){
    }
    
    public void move(int x, int y) { //teleport
        setXY(x,y);
    }
                
    
    public void move(DirectionType direction) { 
        int x, y;
        x = getX();
        y = getY();
        switch (direction) {
            case RIGHT : x += 1; break; //right
            case LEFT : x -= 1; break; //left
            case DOWN : y += 1; break; //down
            case UP : y -= 1; break; //up
        }
        setXY(x,y);
    }
    
    public void changeHealth(double value) {
        setHealth(getHealth() + value);
    }
}
