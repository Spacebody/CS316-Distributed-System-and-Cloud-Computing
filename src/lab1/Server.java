package lab1;

import java.rmi.registry.Registry;

import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	
    public Server() {}

    public static void main(String args[]) {
		try {
			UserAdmin robj = new UserAdminImpl();
			UserAdmin stub = (UserAdmin) UnicastRemoteObject.exportObject(robj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("UserAdmin", stub);
			System.out.println("Server is ready to listen...");
		} catch (Exception e) {
            System.err.println("Server exception thrown: " + e.toString());
            e.printStackTrace();
        }
    }

}
