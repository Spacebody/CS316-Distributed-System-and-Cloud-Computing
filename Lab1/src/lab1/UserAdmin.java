package lab1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;

public interface UserAdmin extends Remote{
	 public boolean register(String userName, String userPassword)
             throws RemoteException;
	 public boolean login(String userName, String userPassword)
             throws RemoteException;
}
