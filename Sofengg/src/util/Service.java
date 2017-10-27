package util;

import java.util.ArrayList;

import model.User;
import model.Item;

public class Service {
	
	//assume logging in will only return one user 
	public static User logIn(String user, String pass){
		ArrayList<User> users = Query.getInstance().userQuery("select * from users where username = '"+user+"' and password = '"+pass+"';");
		
		if(users.size() > 0)
			//log in success
			return users.get(0);
		else
			return null;
	}
	
	//no filter/search
	public static ArrayList<Item> allItems(){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items;");
		return items;
	}
	
	//search
	public static ArrayList<Item> searchItems(String search){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items where concat(name, description, category, manufacturer) like '%" + search + "%';");
		if (items.size() == 0)
			//no matches
			return null;
		return items;
	}
}
