/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author papageno
 */
public class Server {
    final static double VERSION = 0.0001;
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(1234);
        ArrayList<ClientThread> liste = new ArrayList<ClientThread>();
        CacheDB cache = new CacheDB();
        Socket s;
        ClientThread c;
        
        System.out.println("Start - Server ");
        while(true){
            s = server.accept();
            c = new ClientThread(s, liste, cache);
            liste.add(c);
            c.start();
            System.out.println("Connection from "+c.getName());
        }
    }
}
