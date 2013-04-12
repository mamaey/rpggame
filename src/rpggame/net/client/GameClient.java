/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpggame.models.User;

/**
 *
 * @author papageno
 */
public class GameClient extends MainClient{
    public GameClient() throws UnknownHostException, IOException{
        super();
    }
    public GameClient(String host, int port) throws UnknownHostException, IOException{
        super(host,port);
    }
    public GameClient(Socket s) throws IOException{
        super(s);
    }
    
    public static void main(String[] args){
        try {
            GameClient c = new GameClient();
            User user = c.login("mamaey","martin");
            if(user!=null){
                System.out.println("Login erfolgreich!");
                System.out.println(c.getMap(user.getMap().getId()));
                System.out.println(user.getX());
                System.out.println(user.getY());
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
