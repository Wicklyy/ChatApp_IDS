package Client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Interface.userItf;

public class userImpl implements userItf {
    String userName;
    ArrayList<String> messages;
    Semaphore sem;

    userImpl(String userName){
        this.userName = userName;
        messages = new ArrayList<>();
        sem = new Semaphore(0);
    }

    @Override
    public String getUserName() throws RemoteException {
        return userName;
    }


    //synchronized n'est pas vraiment necessaire mais juste au cas ou
    @Override
    public synchronized void putMessage(String message) {
        messages.add(message);
        sem.release();
    }

    @Override
    public String retreiveMessage() {
        try{sem.acquire();}
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return messages.remove(0);
    }
    
}
