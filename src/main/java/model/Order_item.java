package model;

public class Order_item {

	private int id;
	private int orderid;
	private int productid;
	private int quantity;
	private double unit_price;
	private double sub_total;
	
	public Order_item() {
		
	}
	
	public Order_item(int id, int orderid, int productid, int quantity, double unit_price, double sub_total) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.sub_total = sub_total;
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

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getSub_total() {
		return sub_total;
	}

	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	
}
