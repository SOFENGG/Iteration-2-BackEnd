package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public class Query {
	private static Query instance = new Query();
	
	private Query(){
		
	}
	
	public static Query getInstance(){
		return instance;
	}
	
	//handles user related queries
	public ArrayList<User> userQuery(String query){
		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				User user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("user_level"));
				users.add(user);
			}
			Database.getInstance().queryClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//handles item related queries
	public ArrayList<Item> itemQuery(String query){
		ArrayList<Item> items = new ArrayList<Item>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				Item item = new Item(rs.getInt("item_code"), rs.getString("name"), rs.getString("description"), rs.getString("category"), rs.getString("manufacturer"),
						rs.getInt("supplier_code"), rs.getInt("stock"), rs.getDate("date_purchase"), rs.getBigDecimal("price_supplier"), rs.getBigDecimal("price_customer"));
				items.add(item);
			}
			Database.getInstance().queryClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	//add more query for each class
}
