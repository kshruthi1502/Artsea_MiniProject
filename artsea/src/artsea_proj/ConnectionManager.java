package artsea_proj;
import java.sql.*;
public class ConnectionManager {

	private static Connection con;
	public static Connection getConnection() throws Exception{
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","it19737105","shru15");
		return con;
		
	}
	
}
