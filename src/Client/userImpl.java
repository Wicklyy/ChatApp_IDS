package Client;

import java.rmi.RemoteException;

import Interface.userItf;

public class userImpl implements userItf {
    String userName;

    userImpl(String userName){
        this.userName = userName;
    }

    @Override
    public String getUserName() throws RemoteException {
        return userName;
    }

    @Override
    public void putMessage(String message) {
        System.out.println(message);
        //messages.add(message);
        //sem.release();
    }
}
