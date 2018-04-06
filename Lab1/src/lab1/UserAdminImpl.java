package lab1;

import java.rmi.RemoteException;
import java.sql.*;

public class UserAdminImpl implements UserAdmin {	
	 
	private static Connection con = null;
	private  String diverClass = "com.mysql.jdbc.Driver";
    private  String url = "jdbc:mysql://localhost:3306/UserData?autoReconnect=true&useSSL=false";
    private  String name = "root";
    private  String password = "";

	
	public UserAdminImpl() {
		try {
			Class.forName(diverClass);
		}catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
	    try{
			this.con = DriverManager.getConnection(this.url, this.name, this.password);
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	@Override
	public boolean register(String userName, String userPassword) throws RemoteException{
		try {
		    //exam username existence
		    PreparedStatement test_state = this.con.prepareStatement("select * from UserInfo where username=?");
		    test_state.setString(1,userName);
		    ResultSet result = test_state.executeQuery();
            if(!result.next()){
                //if username does not exist, can be registered
                PreparedStatement pre_state = this.con.prepareStatement("insert into UserInfo (username, password, date)"+"values(?,?,?)");
                pre_state.setString(1, userName);
                pre_state.setString(2, userPassword);
                pre_state.setDate(3, new java.sql.Date(new java.util.Date().getTime())); //date
                pre_state.executeUpdate();
                pre_state.close();
                return true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean login(String userName, String userPassword) throws RemoteException{
		PreparedStatement state = null;
		try {
			state = con.prepareStatement("select password from UserInfo where username=?");
			state.setString(1, userName);
            ResultSet result = state.executeQuery();
			if(result.next() && (result.getString("password")).equals(userPassword)) {
                //if username exists and password is correct, then user can login
			    return true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
