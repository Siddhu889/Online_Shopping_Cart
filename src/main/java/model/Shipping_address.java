package model;

public class Shipping_address {

	private int id;
	private int customerid;
	private String full_name;
	private String mobile_number;
	private String address_line;
	private String city;
	private String state;
	private String pincode;
	private String address_type;
	
	public Shipping_address() {
		
	}
	
	public Shipping_address(int id, int customerid, String full_name, String mobile_number, String address_line,
			String city, String state, String pincode, String address_type) {
		this.id = id;
		this.customerid = customerid;
		this.full_name = full_name;
		this.mobile_number = mobile_number;
		this.address_line = address_line;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.address_type = address_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getAddress_line() {
		return address_line;
	}

	public void setAddress_line(String address_line) {
		this.address_line = address_line;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddress_type() {
		return address_type;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	
}
