package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface userItf extends Remote{

    public String getUserName() throws RemoteException;

    public void putMessage(String message);

    public String retreiveMessage();

}
