package dao;

import java.util.List;

import model.Category;
import model.Customer;

public interface CustomerDAO {

	void addCustomer(Customer customer);
	
	Customer getCustomerByEmail(String email);
	
	int getTotalCustomers();
}