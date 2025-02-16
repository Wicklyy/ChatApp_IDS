package Client;

import java.rmi.RemoteException;
import Client.UI.ChatList;

import Interface.userItf;

public class userImpl implements userItf {
    String userName;
    ChatList chatList;

    userImpl(String userName){
        this.userName = userName;
    }

    void bind(ChatList chatList){ this.chatList = chatList;}
    @Override
    public String getUserName() throws RemoteException {
        return userName;
    }

    @Override
    public void putMessage(String message) {
        String[] parts = message.split(" ", 2);
        chatList.addMessage(parts[0], parts[1]);
    }
}
