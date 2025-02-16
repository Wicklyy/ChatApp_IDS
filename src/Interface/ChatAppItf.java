package Interface;


import java.rmi.*;
import java.util.Vector;

public interface ChatAppItf extends Remote{

    public void connect(userItf client) throws RemoteException;
    
    public void disconnect(userItf client)throws RemoteException;

    public void join(userItf client, String chanel) throws RemoteException;

    public void leave(userItf client, String chanel)throws RemoteException;

    public void sendMessage(userItf client, String chanel ,String Message) throws RemoteException;

    public String requestHistory(String chanel) throws RemoteException;
    
    public Vector<String> getList(userItf client) throws RemoteException;
}
