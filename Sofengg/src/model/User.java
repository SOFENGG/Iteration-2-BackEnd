package model;

public class User {
	public static final String TABLE = "users";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_USER_LEVEL = "user_level";
	
	public static final int MANAGER_LEVEL = 2;
	public static final int CASHIER_LEVEL = 1;
	
	private int userID;
	private String name;
	private String user;
	private String pass;
	private int accessLevel;
	
	public User(int userID, String name, String user, String pass, int accessLevel){
		this.userID = userID;
		this.name = name;
		this.user = user;
		this.pass = pass;
		this.accessLevel = accessLevel;
	}
	
	public int getID(){
		return userID;
	}
	public String getName(){
		return name;
	}
	public String getUser(){
		return user;
	}
	public String getPass(){
		return pass;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
}
