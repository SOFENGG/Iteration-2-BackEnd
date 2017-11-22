package model;

import java.math.BigDecimal;

public class Customer {
	public static final String TABLE = "customers";
	public static final String COLUMN_ACCOUNT_ID = "account_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CONTACT_NUMBER = "contact_number";
	public static final String COLUMN_TOTAL_VISITS = "total_visits";
	public static final String COLUMN_DEBT = "debt";
	public static final String COLUMN_DEBT_LIMIT = "debt_limit";
	
	private int account_id;
	private String name;
	private String address;
	private int contactNumber;
	private int totalVisits;
	private BigDecimal debt;
	private BigDecimal debt_limit;
	
	public Customer(int account_id, String name, String address, int contactNumber, int totalVisits, BigDecimal debt,
			BigDecimal debt_limit) {
		this.account_id = account_id;
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.totalVisits = totalVisits;
		this.debt = debt;
		this.debt_limit = debt_limit;
	}

	public int getAccount_id() {
		return account_id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public int getTotalVisits() {
		return totalVisits;
	}

	public BigDecimal getDebt() {
		return debt;
	}

	public BigDecimal getDebt_limit() {
		return debt_limit;
	}
	
}
