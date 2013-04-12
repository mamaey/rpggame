/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.client;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import rpggame.models.Connection;
import rpggame.models.Dot;
import rpggame.models.DotType;
import rpggame.models.Map;
import rpggame.net.Xml;

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
    public Map getMap(int id) throws IOException{
        Map m = new Map();
        send(Connection.Command.MAP.name());
        send(""+id);
        try {
            String lastmsg = in.readLine();
            while(!lastmsg.contains(Connection.Command.END.name())){
                m = Xml.<Map>getObj(Map.class,lastmsg);
                lastmsg = in.readLine();
            }
        } catch (JAXBException ex) {
            throw new IOException("Cound not load Map");
        }        
        return m;
    }
}
