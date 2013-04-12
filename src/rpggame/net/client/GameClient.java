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
            if(c.login("martin","mueller")!=null){
                System.out.println("Login erfolgreich!");
            }
            c.logout();
            if(c.login("martin","amueller")==null){
                System.out.println("Login nicht möglich");
            }
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
