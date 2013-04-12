/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import java.awt.Color;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Andre
 */
@XmlRootElement
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
    public Dot(){
    }
    
    
    public int getLength () {
        return length;
    }
    
    @XmlAttribute
    public void setHeight(int height) {
        this.height = height;
    }
    
    @XmlAttribute
    public void setLength(int length){
        this.length=length;
    }
    
    public int getHeight () {
        return height;
    }
    
    public Color getColour () {
        return colour;
    }
    
    @XmlJavaTypeAdapter(ColorAdapter.class)
    @XmlAttribute
    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    public DotType getDotType () {
        return dotType;
    
    }
    @XmlAttribute
    public void setDotType(DotType dotType) {
        this.dotType = dotType;
    }
    
}
class ColorAdapter extends XmlAdapter<String,Color> {
  public Color unmarshal(String s) {
    return new Color(Integer.parseInt(s),true);
  }
  public String marshal(Color c) {
    return ""+c.getRGB();
  }

}
