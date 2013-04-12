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
public class Map {
    Dot[][] map;
    int id;
    String name;
    int mapLength, mapHeight; //number of fields 
    int dotLength, dotHeight; //size of field (pixel,..)
    
    public Map(int mapLength, int mapHeight, int dotLength, int dotHeight) {
        this.mapLength = mapLength;
        this.mapHeight = mapHeight;
        this.dotLength = dotLength;
        this.dotHeight = dotHeight;
        map = new Dot[mapLength][mapHeight];
    }
    
    public Map(){   
    }
    
    public void setDimision(int mapLength, int mapHeight, int dotLength, int dotHeight) {
        this.mapLength = mapLength;
        this.mapHeight = mapHeight;
        this.dotLength = dotLength;
        this.dotHeight = dotHeight;
        map = new Dot[mapLength][mapHeight];
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String toString(){
        return getName();
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapLength() {
        return mapLength;
    }

    public int getDotHeight() {
        return dotHeight;
    }

    public int getDotLength() {
        return dotLength;
    }
    
    
    //takes dotLength, dotHeight from attributes
    public void createDot(int x, int y ,Color colour, DotType dotType) {
        map[x][y] = new Dot(dotLength, dotHeight, colour, dotType);
    }
    
    public void createDot(int x, int y, Dot dot) {
        map[x][y] = dot;
    }
     public void fillMap(Dot dot) {
        for (int x = 0; x < mapLength; x++) {
            for (int y = 0; y < mapHeight; y++) {
                map[x][y] = dot;
            }
        }
    }
    
    
}
