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

    public void connect(userItf client) {
        connected.add(client);
    }

    @Override
    public void disconnect(userItf client) {
        connected.remove(client);
    }

    @Override
    public void sendMessage(userItf client, String message) throws RemoteException {
        history+=message+"\n";
        for(userItf c : connected){
            if(c != client) c.putMessage(client.getUserName() + " " +message);
        }
    }

    @Override
    public String requestHistory() {
        return history;
    }   
}
