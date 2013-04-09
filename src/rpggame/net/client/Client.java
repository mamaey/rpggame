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
import rpggame.net.Xml;

public class Client extends Thread{
    final static double VERSION=0.0001;
    
    private Socket s;
    private BufferedWriter out;
    private BufferedReader in;
    private boolean flag;
    
    private String lastmsg;
    private Connection conn;
    
    private boolean login;
    
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
    private void send(String str) throws IOException{
        out.write(str);
        out.newLine();
        out.flush();
    }
    public boolean login(String username, String password) throws IOException, Exception{
        if(login)
            throw new Exception("Already Login!");
        try{
            send(Connection.Command.LOGIN.name());
            send(Xml.<Login>getXML(new Login(username,password)));
            if(in.readLine().trim().equalsIgnoreCase(Connection.Boolean.True.name())){
                login=true;
                return true;
            }
        } catch (JAXBException ex) {
            throw new IOException("Connection Problem!");
        }
        login=false;
        return false;
    }
    
    public void logout() throws Exception{
        if(!login)
            throw new Exception("Not Logged in!");
        send(Connection.Command.LOGOUT.name());
        if(!(in.readLine().equalsIgnoreCase(Connection.Boolean.True.name())))
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
    
    public static void main(String[] args){
        try {
            Client c = new Client();
            if(c.login("martin","mueller")){
                System.out.println("Login erfolgreich!");
            }
            c.logout();
            if(!c.login("martin","amueller")){
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
