/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Messages {
    private char from;
    private int id;
    private String msg;

    @XmlAttribute
    public void setFrom(char from) {
        this.from = from;
    }
    public char getFrom() {
        return from;
    }

    public boolean isServer(){
        return (from == 'c');
    }
    
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    @XmlElement
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    
}
