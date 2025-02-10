package Server;
import java.rmi.RemoteException;
import java.util.Vector;

import Interface.ChatAppItf;
import Interface.userItf;

public class ChatAppImpl implements ChatAppItf{
    Vector<userItf> connected;
    String history;

    ChatAppImpl(){
        connected = new Vector<>();
    }

    public void connect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has joined");
        connected.add(client);
    }

    @Override
    public void disconnect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has left");
        connected.remove(client);
    }

    @Override
    public void sendMessage(userItf client, String message) throws RemoteException {
        String send = client.getUserName() + ": " + message;
        System.out.println(send);
        history+= send +"\n";
        for(userItf c : connected){if(!c.getUserName().equals(client.getUserName()) ) c.putMessage(send);}
    }

    @Override
    public String requestHistory() {
        return history;
    }   
}
