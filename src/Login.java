import java.sql.Connection;
import java.util.Scanner;


public class Login {
	
	
	
	private static Scanner sc;
	private static int db;
	

	public void login() {
		
		System.out.println("Please choose which data base... \n1 for mongo \n2 for oracle");
		sc = new Scanner(System.in);
		
		db = sc.nextInt();
		
		MongoDBDBCon mongoCon = new MongoDBDBCon();
		OracleDBCon oracleDBcon = new OracleDBCon();
		//OracleDBcon close
		
		//while(true) {
			
			switch (db) {
			
			case 1 :
				//mongoCon.mongodbcon();
				//mongoCon.table();
				mongoCon.searchDoc();
				break;
				
			
			case 2:
				Connection con = oracleDBcon.oracledb();
				oracleDBcon.closeConn(con);
				//OracleDBcon.testQueryProd();
				//OracleDBcon.testQueryEmpl();
				break;
				
			case 3:
				return;
				
				default:
					System.out.println("chooooooooose something...");
					break;
					
			}
			
		//}
		
	}

}
