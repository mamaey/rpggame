package rpggame.net.server;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

import rpggame.models.Messages;
import rpggame.models.Connection;
import rpggame.models.Dot;
import rpggame.models.DotType;
import rpggame.models.Login;
import rpggame.models.Map;
import rpggame.models.User;
import rpggame.net.Xml;

public class ClientThread extends Thread{
    private Socket s;
    private BufferedWriter out;
    private BufferedReader in;
    private List<ClientThread> clientList;
    private String lastmsg,log;
    private int id;
    private static int idsetter=0;
    private boolean flag;
    private boolean login;
    private CacheDB cacheDB;
    
    public ClientThread(Socket s, List<ClientThread> clientList, CacheDB cacheDB) throws IOException{
        this.s = s;
        this.cacheDB = cacheDB;
        idsetter++;
        this.id = idsetter;
        this.clientList = clientList;
        flag=true;
        login=false;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        try {
            send(Xml.<Connection>getXML(new Connection(id,Server.VERSION)));
        } catch (JAXBException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            disconnect();
        }
    }
    
    public BufferedWriter getWriter(){
        return out;
    }
    public BufferedReader getReader(){
        return in;
    }
    public int getID(){
        return id;
    }
    
    public void disconnect() throws IOException{
        out.close();
        in.close();
        s.close();
        flag = false;
        clientList.remove(this);
    }
    private void send(String str) throws IOException{
        out.write(str);
        out.newLine();
        out.flush();
    }
    @Override
    public void run() {
        try {
            while(flag){
                lastmsg = in.readLine();
                if(lastmsg!=null){
                    log = id+":"+lastmsg;
                    Connection.Command cmd = Connection.Command.valueOf(lastmsg.trim());
                    switch(cmd)
                    {
                        case LOGIN:
                            try {
                                lastmsg=in.readLine();
                                Login loginObj = Xml.<Login>getObj(Login.class,lastmsg);
                                User user = cacheDB.getUser(loginObj);
                                if(user.equals(loginObj)){                                    
                                    send(Xml.<User>getXML(user));
                                    login = true;
                                    log +=" AGREE";
                                }else{
                                    send(Xml.<User>getXML(null));
                                    log +=" DISAGREE";
                                    login = false;
                                }
                            } catch (JAXBException ex) {
                                login = false;
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            send(Connection.Command.END.name());
                            break;
                        case LOGOUT:
                            login = false;
                            send(Connection.Boolean.True.name());
                            break;
                        case DISCONNECT:
                            send("ByBy");
                            disconnect();
                            log +=" CLOSED";
                            break;
                        case MAP:
                            lastmsg=in.readLine();
                            try {
                                send(Xml.<Map>getXML(cacheDB.getMap(Integer.parseInt(lastmsg))));
                            } catch (JAXBException ex) {
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            send(Connection.Command.END.name());
                            break;
                        case MAPLIST:
                            for(Map m: cacheDB.getMapList()){
                                try {
                                    send(Xml.<Map>getXML(m));
                                } catch (JAXBException ex) {
                                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            send(Connection.Command.END.name());
                            break;
                        case MAPEDIT:
                            Map m;
                            lastmsg = in.readLine();
                            while(!lastmsg.contains(Connection.Command.END.name()))
                            {
                                try {
                                    m = Xml.<Map>getObj(Map.class,lastmsg);
                                    cacheDB.setMap(m);
                                } catch (JAXBException ex) {
                                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                lastmsg = in.readLine();
                            }
                            break;
                    }
                    System.out.println(log);
                }else{
                    try {
                        sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
