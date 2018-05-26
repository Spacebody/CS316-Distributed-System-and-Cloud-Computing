import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginServer {

	public static void main(String[] args) {
		// CheckerImpl checkinstanse = new CheckerImpl();
		RegisterImpl registerinstanse = new RegisterImpl();
		try {
			// Checker stub = (Checker)
			// UnicastRemoteObject.exportObject(checkinstanse, 0);
			// Register stub_r = (Register)
			// UnicastRemoteObject.exportObject(registerinstanse, 0);
			// LocateRegistry.createRegistry(2004);
			//// Registry registry = LocateRegistry.getRegistry();
			// Registry registry = LocateRegistry.getRegistry(2004);
			//// registry.rebind("Checker", stub);
			// registry.rebind("Register", stub_r);

			RemoteObjectRef ror = new RemoteObjectRef("127.0.0.1", 2048, 1, "Register");
			// locate.
			//LocateRegistry.createRegistry(1099);
			SimpleRegistry sr = LocateSimpleRegistry.getRegistry("127.0.0.1", 1099);

			System.out.println("located." + sr + "/n");

			if (sr != null) {
				// bind.
				sr.rebind("RegisterServer", ror);

				// test the binding by looking up.
				RemoteObjectRef ror2 = sr.lookup("RegisterServer");

				System.out.println("IP address is " + ror2.IP_adr);
				System.out.println("Port num is " + ror2.Port);
				System.out.println("Object key is " + ror2.Obj_Key);
				System.out.println("Interface name is " + ror2.Remote_Interface_Name);

			} else {
				System.out.println("no registry found.");
			}

			// Register stub_r = RemotePetSkeleton();
			CM.export(ror,registerinstanse);
            SQLAgent.start();
            CM.start();

			System.out.println("Checker server is ready to listen...");
			System.out.println(InetAddress.getLocalHost().getHostName());

		} catch (Exception e) {
			System.err.println("Server exception thrown: " + e.toString());
			e.printStackTrace();
		}

	}

}
