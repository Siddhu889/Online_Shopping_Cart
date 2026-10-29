package model;

import java.time.LocalDateTime;

public class Customer {

	private int id;
	private String name;
	private String email;
	private String phone_number;
	private String password;
	private LocalDateTime registration_date;
	private String status;
	
	public Customer() {
		
	}
	
	public Customer(int id, String name, String email, String phone_number, String password, LocalDateTime datetime,
			String status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.registration_date = datetime;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getDatetime() {
		return registration_date;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.registration_date = datetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
