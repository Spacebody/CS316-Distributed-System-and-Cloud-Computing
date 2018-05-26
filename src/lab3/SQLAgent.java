import java.sql.*;

public class SQLAgent {
	// 创建静态全局变量
	static Connection conn;
	static Statement st;
	static ResultSet rs;
	
	public static void start(){
		try {
			// 加载Mysql数据驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 创建数据连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserData?autoReconnect=true&useSSL=false", "root", "");
		} catch (Exception e) {
			System.out.println("Connect database fail!" + e.getMessage());
		}		
	}

	public static ResultSet executeQuery(String sql) {

		// 同样先要获取连接，即连接到数据库
//		conn = getConnection();
		try {
			// 创建用于执行静态sql语句的Statement对象
			st = (Statement) conn.createStatement();
			// 执行sql查询语句，返回查询数据的结果集
			rs = st.executeQuery(sql);

			return rs;

		} catch (SQLException e) {
			System.out.println("查询数据失败");
			System.err.println("Client exception thrown: " + e.toString());
			e.printStackTrace();
			
		}
		return null;

	}
	public static int executeInsert(String sql) {

		// 同样先要获取连接，即连接到数据库
//		conn = getConnection();
		try {
			// 创建用于执行静态sql语句的Statement对象
			st = (Statement) conn.createStatement();
			// 执行sql查询语句，返回查询数据的结果集			
			return st.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
		return 0;

	}
	public static void close(){
		try {
			// 释放所连接的数据库及资源
			if (null != rs){
				rs.close();
			}
			st.close();
			conn.close(); // 关闭数据库连接

			
		} catch (Exception e) {
			System.out.println("close database fail!" + e.getMessage());
		}		
	}
//	public static Connection getConnection() {
//		// 创建用于连接数据库的Connection对象
//		Connection con = null;
//		try {
//			// 加载Mysql数据驱动
//			Class.forName("com.mysql.jdbc.Driver");
//			// 创建数据连接
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql1", "root", "ZhaoYao0728.");
//		} catch (Exception e) {
//			System.out.println("Connect database fail!" + e.getMessage());
//		}
//		// 返回所建立的数据库连接
//		return con;
//	}
}
