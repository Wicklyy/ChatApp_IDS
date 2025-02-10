package Interface;


import java.rmi.*;

public interface ChatAppItf extends Remote{

    public void connect(userItf client) throws Exception;
    
    public void disconnect(userItf client);

    public void sendMessage(userItf client, String Message) throws RemoteException;

    public String requestHistory();
}
