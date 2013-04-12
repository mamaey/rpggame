/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import rpggame.models.Connection;
import rpggame.models.Map;
import rpggame.net.Xml;

/**
 *
 * @author papageno
 */
public class MapeditorClient extends MainClient{
    public MapeditorClient() throws UnknownHostException, IOException{
        super();
    }
    public MapeditorClient(String host, int port) throws UnknownHostException, IOException{
        super(host,port);
    }
    public MapeditorClient(Socket s) throws IOException{
        super(s);
    }
    public Map[] getMapList() throws IOException{
        ArrayList<Map> map = new ArrayList<Map>();
        if(user.isPrivilegMapeditor()){
            send(Connection.Command.MAPLIST.name());
            String cache = in.readLine();
            try {
                while(!cache.contains(Connection.Command.END.name())){
                    map.add(Xml.<Map>getObj(Map.class, cache));
                    cache = in.readLine();
                }
            } catch (JAXBException ex) {
                Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (Map[])map.toArray();
    }
    /**
     * Add or Replace Map ... id validation
     * @param map
     * @throws IOException 
     */
    public void setMap(Map map) throws IOException{
        if(user.isPrivilegMapeditor()){
            send(Connection.Command.MAPEDIT.name());
            try {
                send(Xml.<Map>getXML(map));
            } catch (JAXBException ex) {
                send(Connection.Command.END.name());
            }
        }
    }
}
