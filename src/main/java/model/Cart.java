package model;

import java.time.LocalDateTime;

public class Cart {

	private int id;
	private int customerid;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private String status;
	
	public Cart() {
		
	}
	
	public Cart(int id, int customer_id, LocalDateTime createdAt, LocalDateTime updateAt, String status) {
		super();
		this.id = id;
		this.customerid = customer_id;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customerid;
	}

	public void setCustomer_id(int customer_id) {
		this.customerid = customer_id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
