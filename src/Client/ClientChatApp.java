package Client;

import java.rmi.registry.*;

import Interface.ChatAppItf;
import Interface.userItf;

import java.util.Vector;

public class ClientChatApp{
    
    public ChatAppItf chat;
    userItf user_stub;

    public ClientChatApp(String host, int port, userItf user_stub){
        connect(host, port, user_stub);
    }


    void connect(String host, int port, userItf user_stub){
        try {
            Registry registry = LocateRegistry.getRegistry(host, port); 
            chat = (ChatAppItf) registry.lookup("ChatAppService");
            
            // Creating the Info RMI
            this.user_stub = user_stub;
      
            chat.connect(user_stub);
            System.out.println("Chat connected");
            }
        catch (Exception e)  {
                System.err.println("Error on client: " + e);
                e.printStackTrace();
        }
      
    }

    public void sendMessage(String chanel, String message){
        try{
            chat.sendMessage(user_stub,chanel, message);
        }
        catch(Exception e){System.err.println("exception trying to send message");e.printStackTrace();}
    }

    void disconnect(){
        try{chat.disconnect(user_stub);}
        catch(Exception e){System.err.println("exception trying to disconnect"); System.exit(1);}
    }

    public String getHistory(String name){
        try{return chat.requestHistory(name);}
        catch(Exception e){e.printStackTrace();}
        return null;
    }

    public Vector<String> getList(){
        try {return chat.getList(user_stub);}
        catch(Exception e){e.printStackTrace();}
        return null;
    }

    public boolean joinChat(String name){
        try{
            chat.join(user_stub, name);
            return true;
        }
        catch(Exception e){e.printStackTrace();}
        return false;
    }

}