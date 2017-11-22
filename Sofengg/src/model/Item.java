package model;

import java.util.Date;
import java.math.BigDecimal;

public class Item {
	public static final String TABLE = "items";
	public static final String COLUMN_ITEM_CODE = "item_code";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_MANUFACTURER = "manufacturer";
	public static final String COLUMN_SUPPLIER_CODE = "supplier_code";
	public static final String COLUMN_STOCK = "stock";
	public static final String COLUMN_RESERVED = "reserved";
	public static final String COLUMN_DATE_PURCHASE = "date_purchase";
	public static final String COLUMN_PRICE_SUPPLIER = "price_supplier";
	public static final String COLUMN_PRICE_CUSTOMER = "price_customer";
	
	private String itemCode;
	private String name;
	private String description;
	private String category;
	private String manufacturer;
	private int supplierCode;
	private int stock;
	private int reserved;
	private Date datePurchaser;
	private BigDecimal priceSupplier;
	private BigDecimal priceCustomer;
	
	public Item(String itemCode, String name, String description, String category, String manufacturer, int supplierCode,
			int stock, int reserved, Date datePurchaser, BigDecimal priceSupplier, BigDecimal priceCustomer) {
		this.itemCode = itemCode;
		this.name = name;
		this.description = description;
		this.category = category;
		this.manufacturer = manufacturer;
		this.supplierCode = supplierCode;
		this.stock = stock;
		this.reserved = reserved;
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

	public String getItemCode() {
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
	
	public int getReserved(){
		return reserved;
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
