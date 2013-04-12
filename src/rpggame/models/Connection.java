package rpggame.models;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Connection {
    private double version;
    private int id;
    
    public static enum Command {DISCONNECT,LOGIN,VERSION,LOGOUT,END,MAPEDIT,MAP, MAPLIST;}
    public static enum Boolean {True,False;}
    
    public Connection(int id, double version){
        this.version = version;
        this.id = id;
    }
    public Connection(){
        this.version = 0.0001;
        this.id = -1;
    }
    
    @XmlAttribute
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    @XmlElement
    public void setVersion(double version) {
        this.version = version;
    }
    public double getVersion() {
        return version;
    }
}
