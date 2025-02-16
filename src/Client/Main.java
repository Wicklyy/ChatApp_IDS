package Client;

import Client.UI.*;
import Interface.ChatAppItf;
import Interface.userItf;

import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame{
    userImpl user;
    userItf user_stub;
    ClientChatApp chatApp;

    Main(String title,String host, int port, String username ){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        user = new userImpl(username);
        try{
            user_stub = (userItf) UnicastRemoteObject.exportObject(user, 0);
            chatApp = new ClientChatApp(host, port, user_stub);
            
            JSplitPane split = new JSplitPane();
		    split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            
            Chat chat = new Chat(chatApp);
	        split.setRightComponent(chat);
            ChatList chatList = new ChatList(chat, chatApp.getList());
            user.bind(chatList);
            split.setLeftComponent(chatList);
            
            add(split);
            split.setVisible(true);
            setSize(600, 400);
            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    cleanUp();
                }
            });
            
        }
        catch(Exception e){e.printStackTrace();}
    }

    void cleanUp(){
        try{
            chatApp.chat.disconnect(user_stub);
            UnicastRemoteObject.unexportObject(user, true);
        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        if (args.length < 3) {
	   System.out.println("Usage: java Main <rmiregistry host> <rmiregistry port> <username>");
	   return;}
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Main("name", args[0], Integer.parseInt(args[1]), args[2]).setVisible(true);
            }
        });
    }
}
