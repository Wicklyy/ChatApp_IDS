package Server;

import java.rmi.RemoteException;
import java.util.Vector;

import Interface.ChatAppItf;
import Interface.userItf;

public class ChatAppImpl implements ChatAppItf{
    Vector<userItf> connected;
    Vector<Chanel> chanels;
    Chanel general;

    ChatAppImpl(){
        connected = new Vector<>();
        chanels = new Vector<>();
        general = new Chanel("General");
        chanels.add(general);
    }

    public void connect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has joined");
        if(connected.contains(client)) throw new RemoteException();
        connected.add(client);
        general.add(client);
        
    }

    public void join(userItf client, String chanel) throws RemoteException{
        for(Chanel c : chanels){
            if(c.name.equals(chanel)) {
                c.add(client);
                return;
            }
        }
        Chanel nc = new Chanel(chanel);
        nc.add(client);
        chanels.add(nc);
        System.out.println("New Chat Room created named: " + chanel);
    }

    public void leave(userItf client, String chanel)throws RemoteException{
        for(Chanel c : chanels){
            if(c.name.equals(chanel)) c.remove(client);
            return;
        }
    }

    @Override
    public void disconnect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has left");
        connected.remove(client);
    }

    @Override
    public void sendMessage(userItf client, String chanel, String message) throws RemoteException {
        String mess = client.getUserName() + ": " + message;
        String send = chanel + " " + mess;
        System.out.println(send);
        Chanel chan = null;
        
        for(Chanel c : chanels) if(c.name.equals(chanel)) chan = c;
        if(chan == null) throw new RemoteException();
        chan.history += mess + "\n";
        for(userItf c : connected) {
            if(chan.connected.get(c.getUserName()) == true) c.putMessage(send);
        }
    }

    @Override
    public String requestHistory(String chanel) {
        for(Chanel c : chanels) if(c.name.equals(chanel)) return c.history;
        return "";
    }

    @Override
    public Vector<String> getList(userItf client) throws RemoteException{
        Vector<String> v = new Vector<>();
        for(Chanel c : chanels) if(c.contains(client.getUserName())) v.add(c.name);
        return v;
    }
}
