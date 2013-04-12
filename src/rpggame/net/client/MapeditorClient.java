/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
}
