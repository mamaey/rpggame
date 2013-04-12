/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

import java.awt.FlowLayout;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import rpggame.models.User;
import rpggame.net.client.MapeditorClient;

/**
 *
 * @author Andre
 */
public class MapEditor extends JFrame{
    public MapeditorClient client;
    public User user;
    
    public MapEditor() throws UnknownHostException, IOException {
        this.client = new MapeditorClient();
        LoginDialog loginDlg = new LoginDialog(this);
        loginDlg.setVisible(true);

        /*
         * 
         * 
         */
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 100);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        MapEditor editor = new MapEditor();
        
    }
}
