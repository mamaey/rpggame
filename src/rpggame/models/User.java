/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author papageno
 */
@XmlRootElement
public class User extends Figure{
    String name, password;
    boolean privilegMapeditor;

    public User(String name, String password, Map map, int x, int y) {
        super(map, x, y);
        this.name = name;
        this.password = cryptPassword(password);
    }
    public User(){
    }
    public User(Map map, int x, int y) {
        super(map, x, y);
    }

    public User(String name, String password) {
        this.name = name;
        this.password = cryptPassword(password);
    }

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute
    public void setPassword(String password) {
        this.password = cryptPassword(password);
    }
    public String getPassword(){
        return password;
    }

    public boolean isPrivilegMapeditor() {
        return privilegMapeditor;
    }

    @XmlAttribute
    public void setPrivilegMapeditor(boolean privilegMapeditor) {
        this.privilegMapeditor = privilegMapeditor;
    }
    
    
    
    public boolean isPassword(String password){
        return this.password.equals(password);
    }
    public boolean isCryptPassword(String password){
        return this.password.equals(cryptPassword(password));
    }
    
    public boolean equals(User u){
        return this.getName().equals(u.getName());
    }
    
    public boolean equals(Login l){
        return ( this.getName().equals(l.getUsername()) && this.isPassword(l.getPassword()) );
    }
    
    /**
     * cryptation For Password
     * @param old
     * @return
     */
    public static String cryptPassword(String old){
        String newPassword;
        newPassword = old.replace("s","a");
        return newPassword;
    }


    @Override
    public String toString() {
        return getName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
