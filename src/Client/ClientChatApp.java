package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import Interface.ChatAppItf;
import Interface.userItf;

public class ClientChatApp{
    
    public static void  main(String [] args) {

	try {
	  if (args.length < 3) {
	   System.out.println("Usage: java HelloClient <rmiregistry host> <rmiregistry port> <username>");
	   return;}

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Registry registry = LocateRegistry.getRegistry(host, port); 
        ChatAppItf chat = (ChatAppItf) registry.lookup("ChatAppService");

        // Creating the Info RMI
        userImpl user = new userImpl(args[2]);
        userItf user_stub = (userItf) UnicastRemoteObject.exportObject(user, 0);
        //Thread t = new Thread(new ClientHandler(user));
        //t.run();
        chat.connect(user_stub);
        System.out.println("Chat connected");

        // Remote method invocation
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        try{
            while ((userInput = stdIn.readLine()) != null) {
                chat.sendMessage(user_stub, userInput);
            }
            chat.disconnect(user_stub);
            
        }catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        UnicastRemoteObject.unexportObject(user, true);

	} catch (Exception e)  {
		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
    }
}