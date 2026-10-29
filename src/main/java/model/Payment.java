package model;

import java.time.LocalDateTime;

public class Payment {

	private int id;
	private int orderid;
	private String payment_method;
	private double amount;
	private String status;
	private String transaction_id;
	private LocalDateTime payment_date;
	
	public Payment() {
		
	}
	
	public Payment(int id, int orderid, String payment_method, double amount, String status, String transaction_id,
			LocalDateTime payment_date) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.payment_method = payment_method;
		this.amount = amount;
		this.status = status;
		this.transaction_id = transaction_id;
		this.payment_date = payment_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public LocalDateTime getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(LocalDateTime payment_date) {
		this.payment_date = payment_date;
	}
	
}
