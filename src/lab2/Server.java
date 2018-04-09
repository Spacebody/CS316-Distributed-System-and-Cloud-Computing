package lab2;

//import java.rmi.registry.Registry;

//import java.rmi.registry.LocateRegistry;
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;

public class Server {
	
    public Server() {}

    public static void main(String args[]) {
		try {
			SimpleRegistry registry = LocateSimpleRegistry.getRegistry("localhost", 1100);
			RemoteObjectRef stub = (RemoteObjectRef) new RemoteObjectRef("localhost",1100, 0, "UserAdmin");
			registry.rebind("UserAdmin", stub);
			registry.lookup("UserAdmin");
			System.out.println("Server is ready to listen...");
		} catch (Exception e) {
            System.err.println("Server exception thrown: " + e.toString());
            e.printStackTrace();
        }
    }

}
