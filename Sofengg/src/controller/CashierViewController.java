package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CartItem;
import model.Database;
import model.Item;
import model.User;
import util.Query;
import view.CashierView;

public class CashierViewController {

	private MainController mc;
	private CashierView cv;
	private User user;
	
	private ArrayList<CartItem> cartItems;
	
	public CashierViewController (MainController mc) {
		this.mc = mc;
		cv = new CashierView (this);
		
		cartItems = new ArrayList<CartItem>();
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
	 
	public void setUser(User user){
		this.user = user;
		System.out.println("Welcome Cashier " + user.getName());
	}
	
	public void logout(){
		this.user = null;
		changeControl(Code.LC_CODE, Code.LOGIN_VIEW);
	}
	
	//cashier view services
	//return
	public boolean returnItem(int itemId){
		//increment stock by 1
		return true;
	}
	
	//get service workers
	public void getServiceWorkers(){
		//query service workers
	}
	
	//insert servicelog
	public void service(int workerId, int serviceId){
		//add service id and worker id to service log
	}
	
	//change price
	public void changePrice(CartItem c, int newPrice){
		c.setPriceSold(c.getQuantity() * newPrice);
	}

	//<-- search functions -->
	//no filter/search
	public ArrayList<Item> allItems(){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items;");
		return items; 
	}
	
	//search
	public ArrayList<Item> searchItems(String search){
		ArrayList<Item> items = Query.getInstance().itemQuery("select * from items where concat(name, description, category, manufacturer) like '%" + search + "%';");
		if (items.size() == 0)
			//no matches
			return null;
		return items;
	}
	
	//<-- transaction functions -->
	public boolean addToCart(int id, int price, int quantity){
		String update = "update items set stock = stock - ? where item_code = ? and stock  >= ?";
		try {
			PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(update);
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			ps.setInt(3, quantity);
			if(Database.getInstance().executeUpdate(ps) == 0)
				return false; //not enough stock
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cartItems.add(new CartItem(id, price * quantity, quantity));
		return true;
	}
	
	public void clearCart(){
		String update = "update items set stock = stock + ? where item_code = ?";
		for(CartItem ci : cartItems){
			try {
				PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(update);
				ps.setInt(1, ci.getQuantity());
				ps.setInt(2, ci.getItemCode());
				Database.getInstance().executeUpdate(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		cartItems.clear();
	}
	
	public void buyItems(){	
		String item_log = "insert into items_log (item_code, type, quantity_sold, price_sold, date_sold) values (?, ?, ?, ?, ?)";
		String accounting = "insert into accounting (user_id, retail, whole_sale, date_sale) values (?, ?, ?, ?)";
		
		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTime().getTime());
		
		for(CartItem ci : cartItems){
			//item_log
			try {
				PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(item_log);
				ps.setInt(1, ci.getItemCode());
				ps.setString(2, "wtf is type?");
				ps.setInt(3, ci.getQuantity());
				ps.setInt(4, ci.getPriceSold());
				ps.setDate(5, today);
				Database.getInstance().executeUpdate(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//accounting
			try {
				PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(accounting);
				ps.setInt(1, user.getID());
				ps.setInt(2, ci.getPriceSold() / ci.getQuantity());
				ps.setInt(3, ci.getPriceSold());
				ps.setDate(4, today);
				Database.getInstance().executeUpdate(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		cartItems.clear();
	}
	
	
}
