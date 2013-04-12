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
    int highest_map_id;

    public CacheDB() {
        mapList = new ArrayList<Map>();
        userList = new ArrayList<User>();
        testData();
        for(Map map: mapList){
            if(highest_map_id<=map.getId())
                highest_map_id = map.getId();
        }
    }
    public void testData(){
        Map map = new Map(32, 32, 32, 32);
        map.fillMap(new Dot(map.getDotLength(), map.getDotHeight(), Color.black, DotType.dtItem));
        map.setId(1);
        mapList.add(map);
        userList.add(new User("mamaey", "martin", mapList.get(0), 2, 2));
        userList.add(new User("phiso", "iso", mapList.get(0), 2, 2));
        userList.add(new User("andre", "test", mapList.get(0), 2, 2));
        for(User user:userList){
            user.setPrivilegMapeditor(true);
        }
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
    public Map getMap(int id){
        for(Map m:mapList)
        {
            if(m.getId() == id)
                return m;
        }
        return null;
    }
    public User getUser(Login login){
        for(User user:userList)
        {
            if(user.getName().equals(login.getUsername()))
                return user;
        }
        return null;
    }

    void setMap(Map m) {
        boolean found=false;
        for(int i=0;i<mapList.size();i++)
        {
            if(mapList.get(i).getId()==m.getId()){
                found = true;
                mapList.set(i, m);
                
            }
        }
        if(!found){
            m.setId(++highest_map_id);
            mapList.add(m);
        }
    }
    
}
