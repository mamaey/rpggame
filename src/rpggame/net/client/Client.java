package rpggame.net.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;


import rpggame.models.Connection;
import rpggame.models.Login;
import rpggame.models.User;
import rpggame.net.Xml;

/*
 * 
 */
public abstract class Client extends Thread{
    final static double VERSION=0.0001;
    
    private Socket s;
    private BufferedWriter out;
    protected BufferedReader in;
    private boolean flag;
    
    private String lastmsg;
    private Connection conn;
    
    private boolean login;
    protected User user;
    
    public Client() throws UnknownHostException, IOException{
        s = new Socket("localhost",1234);
        begin();
    }
    public Client(String host, int port) throws UnknownHostException, IOException{
        s = new Socket(host,port);
        begin();
    }
    public Client(Socket s) throws IOException{
        this.s = s;
        begin();
    }
    public void begin() throws IOException{
        try {
            in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
            flag=true;
            lastmsg = in.readLine();
            conn = Xml.<Connection>getObj(Connection.class,lastmsg);
            if(conn.getVersion()>VERSION)
                throw new UnsupportedClassVersionError("Server Version ist höher als der Client");
        } catch (JAXBException ex) {
            throw new IOException("No connection!");
        }
    }
    protected void send(String str) throws IOException{
        out.write(str);
        out.newLine();
        out.flush();
    }
    public User login(String username, String password) throws IOException, Exception{
        user = null;
        if(login)
            throw new Exception("Already Login!");
        try{
            send(Connection.Command.LOGIN.name());
            send(Xml.<Login>getXML(new Login(username,password)));
            lastmsg = in.readLine();
            while(!lastmsg.equals(Connection.Command.END.name())){
                user = Xml.<User>getObj(User.class,lastmsg);
                if(user !=null){
                    login=true;
                }
                lastmsg = in.readLine();
                System.out.println(lastmsg);
            }
            if(user !=null && login){
                return user;
            }
        } catch (JAXBException ex) {
            throw new IOException("Connection Problem!");
        }
        login=false;
        return user;
    }
    
    public void logout() throws Exception{
        if(!login)
            throw new Exception("Not Logged in!");
        send(Connection.Command.LOGOUT.name());
        if(!(in.readLine().equals(Connection.Boolean.True.name())))
            throw new Exception("ERROR Serverside!");    
        login = false;
    }
    
    public void disconnect() throws IOException{
        send(Connection.Command.DISCONNECT.name());
        out.close();
        in.close();
        s.close();
        flag=false;
    }
    @Override
    public void run(){
        while(flag){
        }
    }
}
