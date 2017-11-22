package model;

public class Supplier {

	public static final String TABLE = "suppliers";
	public static final String COLUMN_SUPPLIER_CODE = "supplier_code";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CONTACT_PERSON = "contact_person";
	public static final String COLUMN_CONTACT_NUMBER = "contact_number";
	public static final String COLUMN_TAX_ID = "tax_id";
	
	private int supplierCode;
	private String name;
	private String contactPerson;
	private int contactNumber;
	private String taxID;
	
	public Supplier(int supplierCode, String name, String contactPerson, int contactNumber, String taxID) {
		this.supplierCode = supplierCode;
		this.name = name;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.taxID = taxID;
	}

	public int getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(int supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
}
