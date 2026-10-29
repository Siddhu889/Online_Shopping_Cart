package model;

import java.time.LocalDateTime;

public class Order {

	private int id;
	private int customerid;
	private String customer_name;
	private int shipping_addressid;
	private LocalDateTime order_date;
	private double total_amount;
	private String status;
	private String payment;
	
	public Order() {
		
	}
	
	public Order(int id, int customer_id, String customer_name, int shipping_address_id, LocalDateTime order_date, double total_amount,
			String status, String payment) {
		super();
		this.id = id;
		this.customerid = customer_id;
		this.customer_name = customer_name;
		this.shipping_addressid = shipping_address_id;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.status = status;
		this.payment = payment;
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
	
	public String getCustomerName() {
	    return customer_name;
	}

	public void setCustomerName(String customername) {
	    this.customer_name = customername;
	}

	public int getShipping_address_id() {
		return shipping_addressid;
	}

	public void setShipping_address_id(int shipping_address_id) {
		this.shipping_addressid = shipping_address_id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
}
