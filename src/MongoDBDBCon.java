import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class MongoDBDBCon {

	static Scanner sc = new Scanner(System.in);
	// static DB db;
	//static String collection;
	static String doc;
	static int values;
	static MongoDatabase db2;

	static String mongoDBAddress = "localhost";

	public void mongodbcon() {
		/*
		 * String collection; String doc; int values;
		 */

	}

	public List<Document> searchAllDoc(String collection) {

		MongoClient mongo = new MongoClient(mongoDBAddress, 27017);

		ArrayList<Document> results = new ArrayList<>();

		try {
			db2 = mongo.getDatabase("nbg_new"); // chooses the DB

			FindIterable<Document> table = db2.getCollection(collection).find().sort(new Document("productID", 1));
			// print

			table.forEach(new Block<Document>() { // iterate over them and print
				public void apply(final Document document) {
					//System.out.println(document);
					results.add(document);
				}
			});
		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}

		return results;
	}

	/**
	 * To search a specific document, using the collection document and the
	 * value (i.e. pair)
	 * 
	 * @param collection
	 * @param doc
	 * @param values
	 * @return
	 */
	public <T> List<Document> searchSpecificDoc(String collection, String doc, T values) {
		MongoClient mongo = new MongoClient("192.168.1.5", 27017);

		ArrayList<Document> results = new ArrayList<>();
		try {
			db2 = mongo.getDatabase("nbg_new");
			System.out.println("woo, connected");

			FindIterable<Document> table = db2.getCollection(collection).find(new Document(doc, values)); // find

			table.forEach(new Block<Document>() { // print out the details of
													// the document
				public void apply(final Document document) {
					//System.out.println(document);
					results.add(document);
				}
			});

		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}

		return results;
	}

	public <T> void returnSpecInfo(T values) {

		List<Document> r = searchSpecificDoc("product", "productID", values);
		for (Document d : r) {
			
			System.out.println("Product ID : " + d.get("productID").toString());
			System.out.println("Description: " + d.get("productDescription").toString());
			if ((!(d.get("customisation") == null))) {
				System.out.println("Customisation : " + d.get("customisation").toString() + "");
			}
		}
	}
	
	public <T> void returnAllInfo(String collection) {
		
		List<Document> r= searchAllDoc(collection);
		for (Document d : r) {
			System.out.println("Product ID : " + d.get("productID").toString());
			System.out.println("Description: " + d.get("productDescription").toString());
			if ((!(d.get("customisation") == null))) {
				System.out.println("Customisation : " + d.get("customisation").toString());

			}

		}
	}

	public void addProductDoc(String collection, int id, String desc, String picture) {

		MongoClient mongo = new MongoClient(mongoDBAddress, 27017);

		System.out.println("Please enter the collection:");
		collection = sc.nextLine();

		System.out.println("Please enter in: \nID \nDescription \nPicture");
		id = sc.nextInt();

		desc = sc.nextLine();

		picture = sc.nextLine();

		try {
			db2 = mongo.getDatabase("nbg_new");

			db2.getCollection(collection).insertOne(new Document().append("productID", id)
					.append("productDescription", desc).append("picture", picture));

		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}

	}

	public void updateDoc(String collection, String field, String inValue, String outValue) {

		MongoClient mongo = new MongoClient(mongoDBAddress, 27017);

		try {

			db2 = mongo.getDatabase("nbg_new");

			db2.getCollection(collection).updateOne(new Document(field, inValue),
					new Document("$set", new Document(field, outValue)));

			searchAllDoc(collection);

		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}
	}
}
