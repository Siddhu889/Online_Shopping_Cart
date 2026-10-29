package model;

public class Cart_item {

	private int id;
	private int cartid;
	private int productid;
	private int quantity;
	private double unit_price;
	private double sub_total;
	private String productName;
	private String productImage;
	
	public Cart_item() {
		
	}
	
	public Cart_item(int id, int cart_id, int product_id, int quantity, double unit_price, double subtotal, String productName, String productImage) {
		
		this.id = id;
		this.cartid = cart_id;
		this.productid = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.sub_total = subtotal;
		this.productName = productName;
	    this.productImage = productImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCart_id() {
		return cartid;
	}

	public void setCart_id(int cart_id) {
		this.cartid = cart_id;
	}

	public int getProduct_id() {
		return productid;
	}

	public void setProduct_id(int product_id) {
		this.productid = product_id;
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

	public double getSubtotal() {
		return sub_total;
	}

	public void setSubtotal(double subtotal) {
		this.sub_total = subtotal;
	}
	
	public String getProductName() {
	    return productName;
	}

	public void setProductName(String productName) {
	    this.productName = productName;
	}

	public String getProductImage() {
	    return productImage;
	}

	public void setProductImage(String productImage) {
	    this.productImage = productImage;
	}
}
