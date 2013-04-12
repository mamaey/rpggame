/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.client;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import rpggame.models.Dot;
import rpggame.models.DotType;
import rpggame.models.Map;

/**
 *
 * @author papageno
 */
public abstract class MainClient extends Client{
    public MainClient() throws UnknownHostException, IOException{
        super();
    }
    public MainClient(String host, int port) throws UnknownHostException, IOException{
        super(host,port);
    }
    public MainClient(Socket s) throws IOException{
        super(s);
    }
    public Map getMap(int id){
        Map m = new Map();
        m.setId(id);
        m.setDimision(32, 32, 5, 5);
        m.fillMap(new Dot(m.getDotHeight(),m.getDotLength(), Color.BLACK, DotType.dtWalkOn));
        return m;
    }
}
