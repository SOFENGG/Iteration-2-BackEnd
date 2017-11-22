package util;

import java.util.ArrayList;

import model.Item;

public class CommonQuery {

	//<-- search functions -->
	
	//no filter/search
	public static ArrayList<Item> allItems(){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from "+Item.TABLE+";");
		return items; 
	}
	
	//search
	public static ArrayList<Item> searchItems(String search){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from "+Item.TABLE+" where concat("+Item.COLUMN_NAME+", "+Item.COLUMN_DESCRIPTION+", "+Item.COLUMN_CATEGORY+", "+Item.COLUMN_MANUFACTURER+") like '%" + search + "%';");
		if (items.size() == 0)
			//no matches
			return null;
		return items;
	}
	
}
