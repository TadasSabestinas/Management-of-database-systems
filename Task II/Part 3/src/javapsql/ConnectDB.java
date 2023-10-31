//DriverManager: This class is used to establish a connection to a database.
//Connection: This interface represents a connection to a database.
//Statement: This interface is used to execute a static SQL statement and obtain the results produced by it.
//ResultSet: This interface represents a table of data that is retrieved from a database.

package javapsql;

import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class ConnectDB
{
	public static void main (String[] args)
	{
		   
		Connection connection = null;
		Statement stmt = null;
		Scanner input = new Scanner(System.in);
		String dbusername; 
		System.out.println("Enter username: ");
		dbusername = input.next();
		String dbpassword; 
		System.out.println("Enter password: ");
		dbpassword = input.next();
		
		
		try 
		{
			Class.forName("org.postgresql.Driver");
			System.out.println("Connecting to database");
			connection = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/biblio", dbusername, dbpassword);
			if(connection != null)
			{
				System.out.println("Connection OK");
			}
			stmt = connection.createStatement();
			int choice = 0;
			while (choice != 8)
			{
				//Display menu
				System.out.println("Choose an operation");
				System.out.println("1. Register a new client");
		        System.out.println("2. View a client");
		        System.out.println("3. Change client's details");
		        System.out.println("4. Delete client's details");
		        System.out.println("5. Show all clients");
		        System.out.println("6. Show all products");
		        System.out.println("7. Insert a new order");
		        System.out.println("8. Exit");
		        System.out.println("Enter your choice: ");
		        choice = input.nextInt();
		        
		        if (choice == 1)
		        {	     
		        	insertRecord(stmt, input);
		        }
		        else if (choice == 2)
		        {
		        	searchRecord(stmt, input);
		        }
		        else if (choice == 3)
		        {
		        	showAllClients(stmt, input);
		        	updateRecord(stmt, input);
		        }
		        else if (choice == 4)
		        {
		        	showAllClients(stmt, input);
		        	deleteRecord(stmt, input);
		        }
		        else if (choice == 5)
		        {
		        	showAllClients(stmt, input);
		        }
		        else if (choice == 6)
		        {
		        	showAllProducts(stmt, input);
		        }
		        else if (choice == 7)
		        {
		        	showAllProducts(stmt, input);
		        	insertNewOrder(stmt, input, connection);
		        }
			}
	}
		catch (SQLException se)
		{
			System.out.println("Couldn't connect to database!");
			se.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException se2)
			{
				
			}
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
		}
 }
	//insertrecord
	public static void insertRecord(Statement stmt, Scanner input) throws SQLException 
	{
    	System.out.println("Inserting a new client");
    	System.out.print("Enter First name: ");
    	String first_name = input.next();
    	System.out.print("Enter Last name: ");
    	String last_name = input.next();
    	System.out.print("Enter date of birth: ");
    	String date_of_birth = input.next();
    	System.out.print("Enter phone number: ");
    	String phone_number = input.next();
    	System.out.print("Enter street name: ");
    	String street = input.next();
    	System.out.print("Enter the number of street: ");
    	String street_number = input.next();
    	System.out.print("Enter city: ");
    	String city = input.next();
    	System.out.print("Zip code: ");
    	String zip_code = input.next();
    	String sql = "INSERT INTO Client (client_id, first_name, last_name, date_of_birth, phone_number, street, street_number, city, zip_code) VALUES (NULL, '" + first_name + "' , '" + last_name + "' , '" + date_of_birth + "', '" + phone_number + "', '" + street + "', '" + street_number + "', '" + city + "', '" + zip_code + "')";
    	stmt.executeUpdate(sql);
    	System.out.println("Client inserted successfully");
	}
	public static void searchRecord (Statement stmt, Scanner input) throws SQLException
	{
		System.out.print("Enter the the ID of the client you are trying to search for: ");
		int client_id = input.nextInt();
		String sql = "SELECT * FROM Client WHERE client_id = " + client_id;
		ResultSet rs = stmt.executeQuery(sql);
		
		//If result is empty, record not found
		if (!rs.next())
		{
			System.out.println("Record not found!");
			return;
		}
		
		int foundID = rs.getInt("client_id");
		String first_name = rs.getString("first_name");
		String last_name = rs.getString("last_name");
		String date_of_birth = rs.getString("date_of_birth");
		String phone_number = rs.getString("phone_number");
		String street = rs.getString("street");
		String street_number = rs.getString("street_number");
		String city = rs.getString("city");
		String zip_code = rs.getString("zip_code");
		System.out.println("Client's ID: " + foundID);
		System.out.println("First name: " + first_name);
		System.out.println("Last name: " + last_name);
		System.out.println("Date of birth: " + date_of_birth);
		System.out.println("Phone number: " + phone_number);
		System.out.println("Street name: " + street);
		System.out.println("Street number: " + street_number);
		System.out.println("City: " + city);
		System.out.println("Zip Code: " + zip_code);	
	}
	public static void updateRecord( Statement stmt, Scanner input) throws SQLException 
	{
	    System.out.println("Enter the id of the client which details you'd like to change: ");
	    int client_id = input.nextInt();
	    System.out.print("Enter the new First name: ");
    	String first_name = input.next();
    	System.out.print("Enter the new Last name: ");
    	String last_name = input.next();
	    System.out.print("Enter the new date of birth: ");
	    String date_of_birth = input.next();
	    System.out.print("Enter the new phone number: ");
	    String phone_number = input.next();
	    System.out.print("Enter the new street name: ");
	    String street = input.next();
	    System.out.print("Enter the new street number: ");
	    String street_number = input.next();
	    System.out.print("Enter the new city: ");
	    String city = input.next();
	    System.out.print("Enter the new zip code: ");
	    String zip_code = input.next();
	    String sql = "UPDATE Client SET first_name = '" + first_name + "' , last_name = '" + last_name + "' , date_of_birth = '" + date_of_birth + "', phone_number = '" + phone_number + "', street = '" + street + "', street_number = '" + street_number + "', city = '" + city + "', zip_code = '" + zip_code + "' WHERE client_id = " + client_id;
	    stmt.executeUpdate(sql);
	    System.out.println("Client's details have been updated successfully");
	}
	public static void deleteRecord(Statement stmt, Scanner input) throws SQLException
	{
		System.out.println("Enter the id of the client which details you want to delete");
		int client_id = input.nextInt();
		String sql = "DELETE FROM Client WHERE client_id = " + client_id;
		stmt.executeUpdate(sql);
		System.out.println("Client deleted successfully");
	}
	public static void showAllClients(Statement stmt, Scanner input) throws SQLException
	{
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM Client");
		while (resultSet.next())
		{
			int client_id = resultSet.getInt("client_id");
			String first_name = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			String date_of_birth = resultSet.getString("date_of_birth");
			String phone_number = resultSet.getString("phone_number");
			String street = resultSet.getString("street");
			String street_number = resultSet.getString("street_number");
			String city = resultSet.getString("city");
			String zip_code = resultSet.getString("zip_code");

			   System.out.println(client_id + " | " + first_name + " | " + last_name + " | " + date_of_birth + " | " + phone_number + " | " + street + " | " + street_number + " | " + city + " | " + zip_code);
		}
	}
	public static void insertNewOrder(Statement stmt, Scanner input, Connection connection) throws SQLException
	{
		connection.setAutoCommit(false);	
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String order_date = dtf.format(now);
			
			System.out.println("Ordering a product...");
			System.out.println("Enter your client ID");
	    	int client_id = input.nextInt();
	    	System.out.print("Enter the ID of the product you are trying to order: ");
	    	int product_id = input.nextInt();
	    	ResultSet resultSet = stmt.executeQuery("SELECT product_price FROM Product WHERE product_id = " + product_id);
	    	resultSet.next();
	    	double product_price = resultSet.getDouble("product_price");
	    	System.out.print("Enter how many you want to buy: ");
	    	int product_quantity = input.nextInt();
	    	String sql = "INSERT INTO Ordering (order_id, client_id, product_id, product_quantity, product_price, order_date, total_order_price) VALUES (NULL, '" + client_id + "' , '" + product_id + "' , '" + product_quantity + "', '" + product_price + "', '" + order_date + "', NULL)";
	    	stmt.executeUpdate(sql);
	    	ResultSet resultSet2 = stmt.executeQuery("SELECT max(order_id) FROM Ordering");
	    	resultSet2.next();
	    	int order_id = resultSet2.getInt("max");
	    	//System.out.println(order_id);
	    	String sql2 = "INSERT INTO Tracking_Detail (tracking_id, order_id, client_id, product_id, shipment_status, shipment_departs, shipment_received) VALUES (NULL, '" + order_id + "' , '" + client_id + "' , '" + product_id + "', '" + "AWAITING SHIPMENT" + "', '" + order_date + "', NULL)";
	    	stmt.executeUpdate(sql2);
	    	connection.commit();
	    	System.out.println("Transaction committed successfully");
	    	System.out.println("Order submitted successfully");
		}
		catch (SQLException e)
		{
			connection.rollback();
			throw e;
		}
		finally
		{
			connection.setAutoCommit(true);
		}
	}
	public static void showAllProducts(Statement stmt, Scanner input) throws SQLException
	{
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM Product");
		while (resultSet.next())
		{
			int product_id = resultSet.getInt("product_id");
			String product_name = resultSet.getString("product_name");
			double product_price = resultSet.getDouble("product_price");
			String status = resultSet.getString("status");
			int product_quantity = resultSet.getInt("product_quantity");

			   System.out.println(product_id + " | " + product_name + " | " + product_price + " | " + status + " | " + product_quantity);
		}
	}
}