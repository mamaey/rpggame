package rpggame.net.server;

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
import rpggame.models.Login;
import rpggame.models.Xml;

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
    
    public ClientThread(Socket s, List<ClientThread> clientList) throws IOException{
        this.s = s;
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
                                if(loginObj.getUsername().charAt(0)==loginObj.getPassword().charAt(0)){
                                    send(Connection.Boolean.True.name());
                                    login = true;
                                    log +=" AGREE";
                                }else{
                                    send(Connection.Boolean.False.name());
                                    log +=" DISAGREE";
                                    login = false;
                                }
                            } catch (JAXBException ex) {
                                login = false;
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
                    }
                    System.out.println(log);
                    /*
                    Messages msg = new Messages();
                    msg.setMsg(lastmsg);
                    msg.setId(id);
                    msg.setFrom('c');
                    try {
                        lastmsg = ;

                    for(ClientThread c: clientList){
                        writer = c.getWriter();
                        if(id != c.getID()){
                            writer.write(                        writer.write());
                            writer.newLine();
                            writer.flush();
                        }
                    }*/
                }else{
                    try {
                        this.sleep(10);
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
