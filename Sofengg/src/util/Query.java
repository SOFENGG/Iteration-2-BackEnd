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
				Item item = new Item(rs.getString(Item.COLUMN_ITEM_CODE),
						rs.getString(Item.COLUMN_NAME),
						rs.getString(Item.COLUMN_DESCRIPTION),
						rs.getString(Item.COLUMN_CATEGORY),
						rs.getString(Item.COLUMN_MANUFACTURER),
						rs.getInt(Item.COLUMN_SUPPLIER_CODE),
						rs.getInt(Item.COLUMN_STOCK),
						rs.getInt(Item.COLUMN_RESERVED),
						rs.getDate(Item.COLUMN_DATE_PURCHASE),
						rs.getBigDecimal(Item.COLUMN_PRICE_SUPPLIER),
						rs.getBigDecimal(Item.COLUMN_PRICE_CUSTOMER));
				
				items.add(item);
			}
			Database.getInstance().queryClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	//transactions
	public ArrayList<Transaction> transactionQuery(String query){
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ResultSet rs = Database.getInstance().query(query);
		
		try {
			while(rs.next()){
				Transaction tran = new Transaction(rs.getInt(Transaction.COLUMN_TRANSACTION_ID),
												rs.getInt(Transaction.COLUMN_USER_ID),
												rs.getString(Transaction.COLUMN_TRANSACTION_TYPE),
												rs.getBoolean(Transaction.COLUMN_IS_LOAN),
												rs.getDate(Transaction.COLUMN_DATE_SOLD),
												rs.getBigDecimal(Transaction.COLUMN_TOTAL_PRICE));
				transactions.add(tran);
			}
			Database.getInstance().queryClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	public int getTransactionCount(){
		ResultSet rs = Database.getInstance().query("select * from " + Transaction.TABLE);
		
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
