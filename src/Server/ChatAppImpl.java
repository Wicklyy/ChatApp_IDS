package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.Vector;

import Interface.ChatAppItf;
import Interface.userItf;
import Client.userImpl;

public class ChatAppImpl implements ChatAppItf{
    Vector<userItf> connected;
    Vector<Chanel> chanels;
    Chanel general;
    PrintStream psC,psM/*,psP*/;

    ChatAppImpl(){
        init();
    }

    private void init(){
        connected = new Vector<>();
        chanels = new Vector<>();
        general = new Chanel("General");
        chanels.add(general);
        recover();
        try{
            // FileOutputStream outPerson = new FileOutputStream("historique_Person");
            FileOutputStream outChan = new FileOutputStream("historique_Chan",true);
            FileOutputStream outMes = new FileOutputStream("historique_Message",true);
            // psP = new PrintStream(outPerson, true, StandardCharsets.UTF_8);
            psC = new PrintStream(outChan, true);
            psM = new PrintStream(outMes, true);
        }
        catch(FileNotFoundException e){
            System.err.println(e);
            System.exit(-1);
        }
    }

    private void recover(){
        try{
            // FileInputStream inP = new FileInputStream("historique_Person");
            FileInputStream inC = new FileInputStream("historique_Chan");
            FileInputStream inM = new FileInputStream("historique_Message");

            int c;
            

            while ((c = inC.read())!=-1) {//Recup Chanels
                String tmp = "";
                do {
                    tmp+=(char)c;
                }while ((c= (char) inC.read())!='\n');

                System.err.println("ajout du chanel : "+tmp+" dans la recovery");
                chanels.add(new Chanel(tmp));
            }


            
            while ((c = inM.read())!=-1) { //recup messages
                String chan="",name="",message="";
                do {
                    chan+=(char)c;
                }while ((c= (char) inM.read())!=' ');

                c= (char) inM.read();
                do {
                    name+=(char)c;
                }while ((c= (char) inM.read())!=':');

                do {
                    message+=(char)c;
                }while ((c= (char) inM.read())!='\n');

                System.err.println("ajout du message : "+message+"dans la recovery");
                for(Chanel ch : chanels){
                    if(ch.name.equals(chan)) {
                        ch.connected.put(name,true);
                        ch.history += name+ " " + message+'\n';
                    }
                }
            }

            inC.close();
            inM.close();
        }catch(IOException e){
            System.err.println(e);
            System.exit(-1);
        }
    }

    public void connect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has joined");
        if(connected.contains(client)) throw new RemoteException();
        connected.add(client);
        general.add(client);

        // psP.print(client.getUserName()); // ecriture des utilisateurs dans l'historique
    }

    public void join(userItf client, String chanel) throws RemoteException{
        for(Chanel c : chanels){
            if(c.name.equals(chanel)) {
                c.add(client);
                return;
            }
        }
        Chanel nc = new Chanel(chanel);
        nc.add(client);
        chanels.add(nc);
        System.out.println("New Chat Room created named: " + chanel);

        psC.print(nc.name + '\n'); // Ecriture des channel dans l'historique
    }

    public void leave(userItf client, String chanel)throws RemoteException{
        for(Chanel c : chanels){
            if(c.name.equals(chanel)) c.remove(client);
            return;
        }
    }

    @Override
    public void disconnect(userItf client) throws RemoteException{
        System.out.println(client.getUserName() + " has left");
        connected.remove(client);
    }

    @Override
    public void sendMessage(userItf client, String chanel, String message) throws RemoteException {
        String mess = client.getUserName() + ": " + message;
        String send = chanel + " " + mess;
        System.out.println(send);
        Chanel chan = null;
        
        for(Chanel c : chanels) if(c.name.equals(chanel)) chan = c;
        if(chan == null) throw new RemoteException();
        chan.history += mess + "\n";
        psM.print(chan.name+" "+mess+"\n"); // Ecriture des messages dans l'historique
        for(userItf c : connected) {

            if(chan.connected.get(c.getUserName()) != null && chan.connected.get(c.getUserName()) == true) c.putMessage(send);
        }
    }

    @Override
    public String requestHistory(String chanel) {
        for(Chanel c : chanels) if(c.name.equals(chanel)) return c.history;
        return "";
    }

    @Override
    public Vector<String> getList(userItf client) throws RemoteException{
        Vector<String> v = new Vector<>();
        for(Chanel c : chanels) if(c.contains(client.getUserName())) v.add(c.name);
        return v;
    }
}
