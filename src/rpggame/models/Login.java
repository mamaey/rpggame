package rpggame.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author papageno
 */
@XmlRootElement
public class Login {
    private String username,password;

    public Login(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
    public Login(){
        setUsername("Anonsmouse");
        setPassword("Error");
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }    
}