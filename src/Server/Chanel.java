package Server;

import java.util.HashMap;


import Interface.userItf;

public class Chanel {
    public HashMap<String,Boolean> connected;
    public String name;
    public String history;

    Chanel(String name){
        this.name = name;
        connected = new HashMap<>();
        history = "";
    }

    boolean contains(String name){
        return connected.get(name)!=null && connected.get(name) == true;
    }

    void add(userItf client){
        try{
            connected.put(client.getUserName(), true);
        }
        catch(Exception e){System.err.println("Error trying to add user: " + client + " in chanel " + name);}
    }

    void remove(userItf client){
        try{
            connected.remove(client.getUserName());
        }catch(Exception e){System.err.println("Error trying to remove user: " + client + " in chanel " + name);}
    }
}
