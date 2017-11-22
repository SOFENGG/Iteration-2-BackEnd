package model;

import java.math.BigDecimal;

public class CartItem {
	private int itemCode;
	private int quantity;
	private BigDecimal price_sold;
	private BigDecimal original_price;
	
	public CartItem(int itemCode, BigDecimal original_price, int quantity){
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.original_price = original_price;
		this.price_sold = original_price;
	}
	
	public BigDecimal getOriginalPrice(){
		return original_price;
	}
	
	public BigDecimal getPriceSold(){
		return price_sold;
	}
	
	public int getItemCode(){
		return itemCode;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setPriceSold(BigDecimal newPrice){
		this.price_sold = newPrice;
	}
}
