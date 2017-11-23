package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Database;
import model.Item;
import model.ItemLog;
import model.ServiceLog;
import model.Transaction;
import model.User;
import util.CommonQuery;
import util.Query;
import view.CashierView;

public class CashierViewController {

	private MainController mc;
	private CashierView cv;
	
	private User cashier;
	private Customer customer;
	
	private ArrayList<CartItem> cartItems;
	private ArrayList<Cart> cartBuffer;
	
	public CashierViewController (MainController mc) {
		this.mc = mc;
		cv = new CashierView (this);
		
		cartItems = new ArrayList<CartItem>();
		cartBuffer = new ArrayList<Cart>();
	}
	
	public Pane getView (int view) {
		switch(view) {
			case Code.CASHER_VIEW: return cv;
		}
		return null;
	}
	
	public Stage getMainStage () {
		return mc.getStage ();
	}
	
	public MainController getMainController() {
		return mc;
	}
	 
	public void changeControl (int requestCode, int view) {
		mc.setScene(requestCode, view);
	}
	 
	public void setUser(User cashier){
		this.cashier = cashier;
		System.out.println("Welcome Cashier " + cashier.getName());
	}
	
	public void logout(){
		this.cashier = null;
		changeControl(Code.LC_CODE, Code.LOGIN_VIEW);
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	//cashier view services
	
	//manager access -> called when action requires manager password
	public boolean managerAccess(String managerPassword){
		boolean accessGranted = false;
		
		if(Query.getInstance().userQuery("select * from " + User.TABLE + " where "+ User.COLUMN_PASSWORD + " = '"+ managerPassword + "' and " + User.COLUMN_USER_LEVEL + " = " + User.MANAGER_LEVEL + ";").size() > 0){
			accessGranted = true;
		}
		
		return accessGranted;
	}
	
	public void setCustomer(int accountId){
		customer = CommonQuery.getCustomerWithId(accountId);
	}
	
	public void removeCustomer(){
		customer = null;
	}
	
	//return
	public void returnItem(String itemCode, int quantity){
		String updateReserved = "update " + Item.TABLE + 
				" set " + Item.COLUMN_RESERVED + " = " + Item.COLUMN_RESERVED + " + ?" + 
				" where " + Item.COLUMN_ITEM_CODE + " = '?'";

		try {
			PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(updateReserved);
			ps.setInt(1, quantity);
			ps.setString(2, itemCode);
			Database.getInstance().executeUpdate(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//override price
	public void overridePrice(CartItem c, BigDecimal newPrice){
		c.setPriceSold(newPrice.multiply(BigDecimal.valueOf(c.getQuantity())));
	}
	
	//insert servicelog
	public void service(int workerId, int serviceId){
		//add service id and worker id to service log
		String service_log = "insert into " + ServiceLog.TABLE + " ("+ServiceLog.COLUMN_SERVICE_ID+
																", "+ServiceLog.COLUMN_WORKER_ID+
																", "+ServiceLog.COLUMN_DATE+") values (?, ?, ?)";
		
		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTime().getTime());
		
		try {
			PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(service_log);
			ps.setInt(1, serviceId);
			ps.setInt(2, workerId);
			ps.setDate(3, today);
			Database.getInstance().executeUpdate(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//<-- transaction functions -->
	
	public boolean addToCart(String itemCode, BigDecimal price, int quantity){
		String updateReserved = "update " + Item.TABLE + 
						" set " + Item.COLUMN_RESERVED + " =  " + Item.COLUMN_RESERVED + " + (" + Item.COLUMN_STOCK + " - ?)" + 
						" where " + Item.COLUMN_ITEM_CODE + " = '?' and " + Item.COLUMN_STOCK + " - " + Item.COLUMN_RESERVED + "  >= ?";
		
		try {
			PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(updateReserved);
			ps.setInt(1, quantity);
			ps.setString(2, itemCode);
			ps.setInt(3, quantity);
			if(Database.getInstance().executeUpdate(ps) == 0)
				return false; //not enough stock
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cartItems.add(new CartItem(itemCode, price.multiply(BigDecimal.valueOf(quantity)), quantity));
		return true;
	}
	
	public void clearCart(){
		String updateReserved = "update " + Item.TABLE + 
								" set " + Item.COLUMN_RESERVED + " = " + Item.COLUMN_RESERVED + " - ?" + 
								" where " + Item.COLUMN_ITEM_CODE + " = '?'";
		
		for(CartItem ci : cartItems){
			try {
				PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(updateReserved);
				ps.setInt(1, ci.getQuantity());
				ps.setString(2, ci.getItemCode());
				Database.getInstance().executeUpdate(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		cartItems.clear();
	}
	
	//hold/unhold cart methods - anj
	public void holdCart(String transactionType) {
		cartBuffer.add(new Cart(cartItems, transactionType));
		cartItems.clear();
	}
	
	public void restoreCart(int index) {
		cartItems.clear();
		cartItems = cartBuffer.get(index).getCartItems();
		//set cart to either whole sale or retail sale (see CartPane commented out function) - anj
		//CartPane.setWoR(cartBuffer.get(index).getTransactionType();
		cartBuffer.remove(index);
	}
	
	public void buyItems(String transactionType, boolean isloan){	
		String item_log = "insert into " + ItemLog.TABLE + " ("+ItemLog.COLUMN_ITEM_CODE+
															", "+ItemLog.COLUMN_TRANSACTION_ID+
															", "+ItemLog.COLUMN_QUANTITY_SOLD+
															", "+ItemLog.COLUMN_ORIGINAL_SOLD+
															", "+ItemLog.COLUMN_PRICE_SOLD+") values (?, ?, ?, ?, ?)";
		
		String updateReserved = "update " + Item.TABLE + 
								" set " + Item.COLUMN_RESERVED + " = " + Item.COLUMN_RESERVED + " - ?"+
								" where " + Item.COLUMN_ITEM_CODE + " = '?'";
		
		String transaction = "insert into " + Transaction.TABLE + " (" + Transaction.COLUMN_TRANSACTION_ID+ 
																	", " + Transaction.COLUMN_USER_ID + 
																	", " + Transaction.COLUMN_TRANSACTION_TYPE + 
																	", " + Transaction.COLUMN_IS_LOAN + 
																	", " + Transaction.COLUMN_DATE_SOLD + 
																	", " + Transaction.COLUMN_TOTAL_PRICE + ") values (?, ?, ?, ?, ?, ?)";
		
		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTime().getTime());
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		int transactionId = Query.getInstance().getTransactionCount() + 1;
		
		for(CartItem ci : cartItems){
			try {
				
				//item_log
				PreparedStatement log = Database.getInstance().getConnection().prepareStatement(item_log);
				log.setString(1, ci.getItemCode());
				log.setInt(2, transactionId);
				log.setInt(3, ci.getQuantity());
				log.setBigDecimal(4, ci.getOriginalPrice());
				log.setBigDecimal(5, ci.getPriceSold());
				
				//update item reserved
				PreparedStatement reserved = Database.getInstance().getConnection().prepareStatement(updateReserved); 
				reserved.setInt(1, ci.getQuantity());
				reserved.setString(2, ci.getItemCode());
				
				//calculates for total price of whole cart
				totalPrice.add(ci.getPriceSold());
				
				Database.getInstance().executeUpdate(log);
				Database.getInstance().executeUpdate(reserved);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//transaction
		try {
			PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(transaction);
			ps.setInt(1, transactionId);
			ps.setInt(2, cashier.getID());
			ps.setString(3, transactionType);
			ps.setBoolean(4, isloan);
			ps.setBigDecimal(5, totalPrice);
			ps.setDate(6, today);
			Database.getInstance().executeUpdate(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(isloan){
			//insert into customer debt
		}
		
		cartItems.clear();
	}
	
}
