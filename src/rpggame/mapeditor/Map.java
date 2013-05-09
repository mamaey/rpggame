/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

/**
 *
 * @author Andre
 */
public class Map {
    Dot[][] map;  
    int mapLength, mapHeight; //number of fields 
    int dotLength, dotHeight; //size of field (pixel,..)
    
    public void Map(int mapLength, int mapHeight, int dotLength, int dotHeight) {
        this.mapLength = mapLength;
        this.mapHeight = mapHeight;
        this.dotLength = dotLength;
        this.dotHeight = dotHeight;
        map = new Dot[mapLength][mapHeight];
    }
    
    public void Map(String path) {
        loadMapFromFile(path);
    }
    
    public void loadMapFromFile(String path) {
        //has to load map-file and initialize all attributes
    }
    
    //takes dotLength, dotHeight from attributes
    public void createDot(int x, int y ,int colour, DotType dotType) {
        map[x][y] = new Dot(x, y, dotLength, dotHeight, colour, dotType);
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
