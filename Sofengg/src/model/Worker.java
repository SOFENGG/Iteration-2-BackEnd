package model;

import java.math.BigDecimal;

public class Worker {
	
	public static final String TABLE = "users";
	public static final String COLUMN_WORKER_ID = "worker_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SALARY = "salary";

	private int workerID;
	private String name;
	private BigDecimal salary;
	
	public Worker(int workerID, String name, BigDecimal salary) {
		this.workerID = workerID;
		this.name = name;
		this.salary = salary;
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
}
