/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Andre
 */



@XmlRootElement
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
    @XmlJavaTypeAdapter(MapAdapter.class)
    @XmlAttribute
    public void setMap(Map map) {
        this.map = map;
    }
    
    public int getX() {
        return x;
    }

    @XmlAttribute
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    
    }
    @XmlAttribute
    public void setY(int y) {
        this.y = y;
    }
    
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double getHealth() {
        return health;
    }
    
    @XmlAttribute
    public void setHealth(double health) {
        this.health = health;
    }
    
    @XmlAttribute
    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
    }
       
    
    public FigureType getFigureType() {
        return figureType;
    }
    
}
class MapAdapter extends XmlAdapter<String,Map> {
    @Override
    public Map unmarshal(String s) {
      Map map = new Map();
      map.setId(Integer.parseInt(s));
      return map;
    }
    @Override
    public String marshal(Map c) {
      return ""+c.getId();
    }
}
