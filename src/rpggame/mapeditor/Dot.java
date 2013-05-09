/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

/**
 *
 * @author Andre
 */
public class Dot {
    private int x, y, length, height, colour;
    private DotType dotType;
    
    public Dot(int x,int y,int length,int height, int colour, DotType dotType) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.colour = colour;
        this.dotType = dotType;
    }
    
    public int getX () {
        return x;
    }
    
    public int getY () {
        return y;
    }
    
    public int getLength () {
        return length;
    }
    
    public int getHeight () {
        return height;
    }
    
    public int getColour () {
        return colour;
    }
    
    public void setColour(int colour) {
        this.colour = colour;
    }
    
    public DotType getDotType () {
        return dotType;
    }
    
    public void setDotType(DotType dotType) {
        this.dotType = dotType;
    }
    
}

