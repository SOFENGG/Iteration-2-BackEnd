package util;

import java.util.ArrayList;

import model.Customer;
import model.Item;
import model.Worker;

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
	
	//search by code
	public static Item searchItemByCode(String code){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from "+Item.TABLE+" where "+Item.COLUMN_ITEM_CODE+" = '"+code+"';");
		if (items.size() == 0)
			//no matches
			return null;
		else
			return items.get(0);
	}
	
	//get all customers
	public static ArrayList<Customer> getAllCustomer(){
		return Query.getInstance().customerQuery("select * from " + Customer.TABLE + ";");
	}
	
	public static Customer getCustomerWithId(int accountId){
		ArrayList<Customer> customer = Query.getInstance().customerQuery("select * from " + Customer.TABLE + " where " + Customer.COLUMN_ACCOUNT_ID + " = " + accountId + ";");
		if(customer.size() != 0)
			return customer.get(0);
		return null;
	}
	
	public static ArrayList<Customer> getCustomerWithAddress(String address){
		ArrayList<Customer> customer = Query.getInstance().customerQuery("select * from " + Customer.TABLE + " where " + Customer.COLUMN_ADDRESS + " like '%" + address + "%';");
		return customer;
	}
	
	public static ArrayList<Customer> getCustomerWithName(String name){
		ArrayList<Customer> customer = Query.getInstance().customerQuery("select * from " + Customer.TABLE + " where " + Customer.COLUMN_NAME + " like '%" + name + "%';");
		return customer;
	}
	
	//service worker
	public static ArrayList<Worker> getWorkers(){
		return Query.getInstance().workerQuery("select * from " + Worker.TABLE + ";");
	}
	
	public static Worker getWorkerWithId(int accountId){
		ArrayList<Worker> worker = Query.getInstance().workerQuery("select * from " + Worker.TABLE + " where " + Worker.COLUMN_WORKER_ID + " = " + accountId + ";");
		if(worker.size() != 0)
			return worker.get(0);
		return null;
	}
	
	public static ArrayList<Worker> getWorkerWithName(String name){
		ArrayList<Worker> worker = Query.getInstance().workerQuery("select * from " + Worker.TABLE + " where " + Worker.COLUMN_NAME + " like '%" + name + "%';");
		return worker;
	}
}
