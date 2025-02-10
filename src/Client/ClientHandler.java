package Client;

public class ClientHandler implements Runnable{
    userImpl user;

    ClientHandler(userImpl user){
        this.user = user;
    }
    
    public void run(){
        while(true){
            System.out.println(user.retreiveMessage());
        }
    }
}
