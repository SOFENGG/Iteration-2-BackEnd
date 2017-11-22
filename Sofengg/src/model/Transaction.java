package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {
	
	public static final String TABLE = "transactions";
	public static final String COLUMN_TRANSACTION_ID = "transaction_id";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
	public static final String COLUMN_IS_LOAN = "is_loan";
	public static final String COLUMN_DATE_SOLD = "date_sold";
	public static final String COLUMN_TOTAL_PRICE = "total_price";

	private int transactionID;
	private int userID;
	private String transactionType;
	private boolean isLoan;
	private Date dateSold;
	private BigDecimal totalPrice;
	
	public Transaction(int transactionID, int userID, String transactionType, boolean isLoan, Date dateSold,
			BigDecimal totalPrice) {
		this.transactionID = transactionID;
		this.userID = userID;
		this.transactionType = transactionType;
		this.isLoan = isLoan;
		this.dateSold = dateSold;
		this.totalPrice = totalPrice;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public boolean isLoan() {
		return isLoan;
	}

	public void setLoan(boolean isLoan) {
		this.isLoan = isLoan;
	}

	public Date getDateSold() {
		return dateSold;
	}

	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
