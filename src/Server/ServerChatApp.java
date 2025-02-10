package Server;
import Interface.ChatAppItf;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerChatApp {
    public static void  main(String [] args) {
        try{
            // Create ChatApp remote object
            ChatAppImpl chat = new ChatAppImpl();
            ChatAppItf chat_stub = (ChatAppItf) UnicastRemoteObject.exportObject(chat, 0);
            Registry registry = null;
	        if (args.length>0)
		        registry= LocateRegistry.getRegistry(Integer.parseInt(args[0])); 
	        else{
		        registry = LocateRegistry.getRegistry();
	            registry.rebind("ChatAppService", chat_stub);
            }
            System.out.println ("Server ready");
        }catch (Exception e) {
            System.err.println("Error on server :" + e) ;
            e.printStackTrace();
        }
    }
}