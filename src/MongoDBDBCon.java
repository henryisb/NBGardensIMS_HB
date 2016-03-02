import java.util.Scanner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoDBDBCon {

	static Scanner sc = new Scanner(System.in);
	static DB db;
	static String collection;
	static String doc;
	static int values;

	//@SuppressWarnings("deprecation")
	public void mongodbcon() {
		/*
		 * String collection; String doc; int values;
		 */

	}

	@SuppressWarnings("deprecation")
	public void searchDoc() {
		
		MongoClient mongo = new MongoClient("localhost", 27017);


		try {
			db = mongo.getDB("nbg_new");
			System.out.println("woo, connected");
			
			System.out.println("Please enter a collection to search");

			collection = sc.nextLine();

			DBCollection table = db.getCollection(collection);

			// getCollection(collection);
			System.out.println("enter doc and then values");
			doc = sc.nextLine();
			values = sc.nextInt();

			BasicDBObject searchQuery = new BasicDBObject().append(doc, values);
			// BasicDBObject searchQuery2 = new BasicDBObject();

			DBCursor cursor = table.find(searchQuery);
			// DBCursor cursor2 = table.find(searchQuery2);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());

			}
		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}

	}

	public void addDoc() {
		
		MongoClient mongo = new MongoClient("localhost", 27017);


		try {
			db = mongo.getDB("nbg_new");
			System.out.println("woo, connected");
			
			System.out.println("Please enter a collection to search");

			collection = sc.nextLine();

			DBCollection table = db.getCollection(collection);
			
			BasicDBObject searchQuery = new BasicDBObject();
			DBCursor cursor = table.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());

			}
		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}


	}


}
