package lab4;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;

public interface UserAdmin extends Remote{
	 boolean register(String userName, String userPassword)
             throws RemoteException;
	 boolean login(String userName, String userPassword)
             throws RemoteException;
}
