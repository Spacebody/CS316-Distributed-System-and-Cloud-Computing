import java.rmi.RemoteException;
import java.lang.String;
import java.sql.Date;


public class RegisterImpl implements Register {
	public boolean store(String username, String password) throws RemoteException{
        // 准备查询语句
		String sql = "insert into UserInfo(username,password, date) values('"+username + "','"+password+"',"+"'2018-03-16 00:00:00')";

		System.out.println(sql);
		SQLAgent.start();
		int row = SQLAgent.executeInsert(sql);
		System.out.println("最后的查询结果为："+row);

		if (1 == row) {
			SQLAgent.close();
			return true;
		} else {
			SQLAgent.close();
			return false;
		}

		
	}
}
