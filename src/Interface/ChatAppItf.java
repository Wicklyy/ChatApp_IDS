package Interface;


import java.rmi.*;

public interface ChatAppItf extends Remote{

    public void connect(userItf client) throws RemoteException;
    
    public void disconnect(userItf client)throws RemoteException;

    public void sendMessage(userItf client, String Message) throws RemoteException;

    public String requestHistory() throws RemoteException;
}
