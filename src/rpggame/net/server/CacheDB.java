/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net.server;

import java.awt.Color;
import java.util.ArrayList;
import rpggame.models.Dot;
import rpggame.models.DotType;
import rpggame.models.Login;
import rpggame.models.Map;
import rpggame.models.User;

/**
 *
 * @author papageno
 */
public class CacheDB {
    ArrayList<Map> mapList;
    ArrayList<User> userList;

    public CacheDB() {
        mapList = new ArrayList<Map>();
        userList = new ArrayList<User>();
        testData();
    }
    public void testData(){
        Map map = new Map(32, 32, 32, 32);
        map.fillMap(new Dot(map.getDotLength(), map.getDotHeight(), Color.black, DotType.dtItem));
        mapList.add(map);
        userList.add(new User("mamaey", "martin", mapList.get(0), 2, 2));
        userList.add(new User("phiso", "iso", mapList.get(0), 2, 2));
        userList.add(new User("andre", "test", mapList.get(0), 2, 2));
    }

    public ArrayList<Map> getMapList() {
        return mapList;
    }

    public void setMapList(ArrayList<Map> mapList) {
        this.mapList = mapList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    public User getUser(Login login){
        for(User user:userList)
        {
            if(user.getName().equals(login.getUsername()))
                return user;
        }
        return null;
    }
    
}
