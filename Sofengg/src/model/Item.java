package model;

import java.util.Date;
import java.math.BigDecimal;

public class Item {
	private int itemCode;
	private String name;
	private String description;
	private String category;
	private String manufacturer;
	private int supplierCode;
	private int stock;
	private Date datePurchaser;
	private BigDecimal priceSupplier;
	private BigDecimal priceCustomer;
	
	public Item(int itemCode, String name, String description, String category, String manufacturer, int supplierCode,
			int stock, Date datePurchaser, BigDecimal priceSupplier, BigDecimal priceCustomer) {
		this.itemCode = itemCode;
		this.name = name;
		this.description = description;
		this.category = category;
		this.manufacturer = manufacturer;
		this.supplierCode = supplierCode;
		this.stock = stock;
		this.datePurchaser = datePurchaser;
		this.priceSupplier = priceSupplier;
		this.priceCustomer = priceCustomer;
	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", name=" + name + ", description=" + description + ", category="
				+ category + ", manufacturer=" + manufacturer + ", supplierCode=" + supplierCode + ", stock=" + stock
				+ ", datePurchaser=" + datePurchaser + ", priceSupplier=" + priceSupplier + ", priceCustomer="
				+ priceCustomer + "]";
	}

	public int getItemCode() {
		return itemCode;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getSupplierCode() {
		return supplierCode;
	}

	public int getStock() {
		return stock;
	}

	public Date getDatePurchaser() {
		return datePurchaser;
	}

	public BigDecimal getPriceSupplier() {
		return priceSupplier;
	}

	public BigDecimal getPriceCustomer() {
		return priceCustomer;
	}
}
