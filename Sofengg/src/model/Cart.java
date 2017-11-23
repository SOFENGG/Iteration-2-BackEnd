package model;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

//for hold/restore cart methods in CashierViewController and hold button/tab in CartPane - anj
public class Cart {
	
	private Date date;
	private ArrayList<CartItem> cartItems;
	private String transactionType;
	
	public Cart(ArrayList<CartItem> cartItems, String transactionType) {
		date = new Date();
		this.cartItems = cartItems;
		
		this.transactionType = transactionType;
	}
	
	//for display in hold tab
	public String getDate() { //returns date in MM/DD/YYYY format
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}
	
	public String getTime() { //returns time in HH:MM AM/PM format
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		return sdf.format(date);
	}

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		for (CartItem ci: cartItems)
			totalPrice.add(ci.getPriceSold());
		return totalPrice;
	}

	//for cart restoration
	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}
	
	//for both display in hold tab as well as cart restoration
	public String getTransactionType() {
		return transactionType;
	}
}
