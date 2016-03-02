import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import com.mysql.jdbc.Statement;
//import com.mysql.jdbc.util.ResultSetUtil;


public class OracleDBCon {

//	private static Connection conn = null;
//	private static Statement stmt = null;
	//private static ResultSet rs = null;
	private static String query = null;
	//private static Scanner sc;

	public  Connection oracledb() {
		
		Connection conn = null;

		String URL = "jdbc:oracle:thin:@localhost:1521/xe";
		String USER = "SYSTEM";
		String PASS = "password";

		
		try {
			 conn = DriverManager.getConnection(URL, USER, PASS);
			
			System.out.println("woooo connected...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn != null) {
			
			//System.out.println("wooooo you win...");
			
		} else {
			System.out.println("failed");
		}
		return conn;

		/*try {
			
			Class.forName("oracle.jdbc.driver.oracleDriver");
			
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}*/
		
	}
	

	public void closeConn(Connection conn){
		
		try {
			conn.close();
			System.out.println("connection closed");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void testQueryEmpl(Scanner sc, ResultSet rs, Connection conn, Statement stmt) {
		
		System.out.println("please enter the query youd like to make... \n1 Select...");
		
		sc = new Scanner(System.in);
		
		
		query = "select * from employee";
		System.out.println("EmployeeID \tRole \t\t\tUsername \tPassword");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(	  rs.getInt("employeeID") + "\t\t" 
									+ rs.getString("role") + "\t\t"
									+ rs.getString("username") + "\t" 
									+ rs.getString("password"));
				/*System.out.println(rs.getString("role"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	
	public void testQueryProd(Scanner sc, ResultSet rs, Connection conn, Statement stmt) {
		
		//System.out.println("please enter the query youd like to make... \n1 Select...");
		
		//sc = new Scanner(System.in);
		
		
		query = "select * from product";
		System.out.println("productID \tproductDescription \tstock Level \tSupplier ID \tproduct location \tproductSize \tproduct price \tmax level \tmin level");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getInt("productid") + "\t\t" 
									+ rs.getString("productdescription") + "\t\t"
									+ rs.getString("stocklevel") + "\t\t\t" 
									+ rs.getString("supplier_supplierid") + "\t"
									+ rs.getString("productlocation_location") + "\t\t\t"
									+ rs.getString("productsize") + "\t\t"
									+ rs.getString("productprice") + "\t\t"
									+ rs.getString("maxlevel") + "\t"
									+ rs.getString("minlevel"));
				/*System.out.println(rs.getString("role"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	

	
}
