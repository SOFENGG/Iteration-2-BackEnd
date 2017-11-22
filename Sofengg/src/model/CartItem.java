package model;

public class CartItem {
	private int itemCode;
	private int quantity;
	private int price_sold;
	
	public CartItem(int itemCode, int price_sold, int quantity){
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.price_sold = price_sold;
	}
	
	public int getPriceSold(){
		return price_sold;
	}
	
	public int getItemCode(){
		return itemCode;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setPriceSold(int newPrice){
		this.price_sold = newPrice;
	}
}
