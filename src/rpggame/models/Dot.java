/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import java.awt.Color;

/**
 *
 * @author Andre
 */
public class Dot {
    private int length, height;
    private Color colour;
    private DotType dotType;
    
    public Dot(int length,int height, Color colour, DotType dotType) {
        this.length = length;
        this.height = height;
        this.colour = colour;
        this.dotType = dotType;
    }
    
    
    public int getLength () {
        return length;
    }
    
    public int getHeight () {
        return height;
    }
    
    public Color getColour () {
        return colour;
    }
    
    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    public DotType getDotType () {
        return dotType;
    }
    
    public void setDotType(DotType dotType) {
        this.dotType = dotType;
    }
    
}

