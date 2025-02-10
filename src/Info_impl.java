import java.rmi.RemoteException;

public class Info_impl implements Info_itf{
    String name;

    Info_impl(String name){
        this.name = name;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
