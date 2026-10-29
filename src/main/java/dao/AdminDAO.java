package dao;

import model.Admin;

public interface AdminDAO {

	void addAdmin(Admin admin); 
	
	Admin getAdminByEmail(String email);

}
