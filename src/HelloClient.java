import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloClient {
  public static void main(String [] args) {
	
	try {
	  if (args.length < 2) {
	   System.out.println("Usage: java HelloClient <rmiregistry host> <rmiregistry port>");
	   return;}

	String host = args[0];
	int port = Integer.parseInt(args[1]);

	Registry registry = LocateRegistry.getRegistry(host, port); 
	Hello h = (Hello) registry.lookup("HelloService");

	// Creating the Info RMI
	Info_impl i = new Info_impl("ME!");
	Info_itf i_stub = (Info_itf) UnicastRemoteObject.exportObject(i, 0);


	// Remote method invocation
	String res = h.sayHello(i_stub);
	System.out.println(res);
	UnicastRemoteObject.unexportObject(i, true);

	} catch (Exception e)  {
//		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
  }
}
