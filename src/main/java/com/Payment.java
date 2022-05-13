package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class Payment {
private Connection connect() {
		Connection con = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");


// Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ electrogrid", "paf", "12345678");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return con;
	}

	public String insertPayment(String cardName, String cardNo, String expDate, String cvv, String cusID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
// create a prepared statement
			String query = " insert into payment(`paymentID`, `cardName`, `cardNo`, `expDate`, `cvv`, `cusID`)"
					+ " values ( ?, ?, ?, ?, ? ,? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cardName);
			preparedStmt.setInt(3, Integer.parseInt(cardNo));
			preparedStmt.setString(4, expDate);
			preparedStmt.setInt(5, Integer.parseInt(cvv));
			preparedStmt.setInt(6, Integer.parseInt(cusID));


// execute the statement
			preparedStmt.execute();
			con.close();

			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
		}

	
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>Card Type</th><th>Card Number</th><th>Expire Date</th><th>CVV Number</th><th>CustomerID</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from payment";

	Statement stmt = (Statement) con.createStatement();
	ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	
	
// iterate through the rows in the result set
	while (rs.next()) {
		String paymentID = Integer.toString(rs.getInt("paymentID"));
		 String cardName = rs.getString("cardName");
		 String cardNo = Integer.toString(rs.getInt("cardNo"));
		 String expDate = rs.getString("expDate");
		 String cvv = Integer.toString(rs.getInt("cvv"));
		 String cusID = Integer.toString(rs.getInt("cusID"));

// Add into the html table
	output += "<tr><td><input id=\'hidPaymentIDUpdate\' name=\'hidPaymentIDUpdate\' type=\'hidden\' value=\'"
+ paymentID + "'>" + cardName + "</td>";
	output += "<td>" + cardNo + "</td>";
	output += "<td>" + expDate + "</td>";
	output += "<td>" + cvv + "</td>";
	output += "<td>" + cusID + "</td>";
	
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-paymentID='" + paymentID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-paymentid='" + paymentID + "'></td></tr>";
	}

	con.close();



// Complete the html table
	output += "</table>";
	} catch (Exception e) {
	output = "Error while reading the payment.";
	System.err.println(e.getMessage());
	}
	
	
	return output;
	}
	
	//Update Payment
	public String updatePayment(String paymentID, String cardName, String cardNo, String expDate, String cvv, String cusID) {
	String output = "";
	
	try {
	Connection con = connect();
	
	if (con == null) {
	return "Error while connecting to the database for updating.";
	}

// create a prepared statement
	String query = "UPDATE payment SET cardName=?,cardNo=?,expDate=?,cvv=?,cusID=? WHERE paymentID=?";
	
	PreparedStatement preparedStmt = con.prepareStatement(query);


// binding values

	preparedStmt.setString(1, cardName);
	preparedStmt.setInt(2, Integer.parseInt(cardNo));
	preparedStmt.setString(3, expDate);
	preparedStmt.setInt(4, Integer.parseInt(cvv));
	preparedStmt.setInt(5, Integer.parseInt(cusID));
	preparedStmt.setInt(6, Integer.parseInt(paymentID));


// execute the statement
	preparedStmt.execute();
	con.close();
		
	String newPayment = readPayment();
	output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	} catch (Exception e) {
	output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";
	System.err.println(e.getMessage());
	}
	
	
	
	return output;
	}
	
	
	

//Delete Payment

	public String deletePayment(String paymentID)
	 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 // create a prepared statement
				 String query = "delete from payment where paymentID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(paymentID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newPayment = readPayment();
					output = "{\"status\":\"success\", \"data\": \"" +
							newPayment + "\"}"; 
				 }
				 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
	 }


}