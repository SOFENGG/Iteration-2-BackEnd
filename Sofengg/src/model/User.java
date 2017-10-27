package model;

public class User {
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
