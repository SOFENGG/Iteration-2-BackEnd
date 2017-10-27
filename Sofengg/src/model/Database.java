package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
	private final String directory = "jdbc:mysql://localhost:3306/autosupply?useSSL=false";
	private final String user = "root";
	private final String pass = "1234";
	
	private static Database instance = new Database();
	
	private Connection con;
	private Statement s;
	private ResultSet rs;
	
	//instantiate the connection with the database
	private Database(){
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(directory, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance(){
		return instance;
	}
	
	//returns the queried rows
	public ResultSet query(String query){
		try {
			s = con.createStatement();
			rs = s.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//this method needs to be called after every query
	public void queryClose(){
		try {
			s.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//closes the connection to the database
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
