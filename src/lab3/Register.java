import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Register extends Remote{
	public boolean store(String username, String password)
            throws RemoteException;

}
