package Server;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.util.Vector;

import Interface.ChatAppItf;
import Interface.userItf;

public class ChatAppImpl implements ChatAppItf{
    Vector<userItf> connected;
    String history = "";
    PrintStream ps;

    ChatAppImpl(){
        connected = new Vector<>();
        try{
            FileInputStream in = new FileInputStream("historique");
            int c;
            while ((c= in.read())!=-1){
                history+=(char)c;
            }
            in.close();
            FileOutputStream out = new FileOutputStream("historique");
            ps = new PrintStream(out, true, StandardCharsets.UTF_8) ;
        }
        catch(FileNotFoundException e){
            System.err.println(e);
            System.exit(-1);
        }
        catch(IOException e){
            System.err.println(e);
            System.exit(-1);
        }
    }

    public void connect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has joined");
        connected.add(client);
        
        if (history != ""){
            client.putMessage(history);
        }
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
        ps.print(send+'\n');
        history+= send +"\n";
        for(userItf c : connected){if(!c.getUserName().equals(client.getUserName()) ) c.putMessage(send);}
    }

    @Override
    public String requestHistory() {
        return history;
    }   
}
