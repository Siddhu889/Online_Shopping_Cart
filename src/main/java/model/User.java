package model;

import java.time.LocalDateTime;

public class User {

	    private int id;
	    private String name;
	    private String email;
	    private String phoneNumber;
	    private String password;
	    private LocalDateTime registrationDate;
	    private String status;
	    
	    public User() {
		
		}
	    
	    
		public User(int id, String name, String email, String phoneNumber, String password,
				LocalDateTime registrationDate, String status) {
			
			this.id = id;
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.password = password;
			this.registrationDate = registrationDate;
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


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public LocalDateTime getRegistrationDate() {
			return registrationDate;
		}


		public void setRegistrationDate(LocalDateTime registrationDate) {
			this.registrationDate = registrationDate;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}
	    
}
