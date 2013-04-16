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
import rpggame.models.User;
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
                while(!cache.equals(Connection.Command.END.name())){
                    map.add(Xml.<Map>getObj(Map.class, cache));
                    cache = in.readLine();
                }
            } catch (JAXBException ex) {
                Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (Map[])map.toArray(new Map[map.size()]);
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
            }
            send(Connection.Command.END.name());
        }
    }
    public static void main(String[] args){
            try {
            MapeditorClient c = new MapeditorClient();
            User user = c.login("phiso","iso");
            if(user!=null){
                System.out.println("Login erfolgreich!");
                System.out.println(user.getMap().getId());
                System.out.println(user.getX());
                System.out.println(user.getY());
                if(user.isPrivilegMapeditor()){
                    Map m = c.getMap(user.getMap().getId());
                    m.setName("First");
                    //überschreiben Test
                    c.setMap(m);
                    m= new Map(10, 10, 10, 10);
                    m.setName("Secound");
                    m.fillMap(new Dot(m.getDotHeight(), m.getDotLength(), Color.red, DotType.dtWalkOn));
                    //hinzufüegen Test
                    c.setMap(m);
                    for(Map map: c.getMapList()){
                        System.out.println(map.getId()+":"+map.getName());
                    }
                    
                }
            }
            c.logout();
            c.disconnect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
