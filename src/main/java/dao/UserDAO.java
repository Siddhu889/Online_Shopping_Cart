package dao;

import java.util.List;
import model.User;

public interface UserDAO {

    List<User> getAllUsers();

    List<User> searchUsers(String searchText);

    String changeStatus(int id);

    String deleteUser(int id);
}