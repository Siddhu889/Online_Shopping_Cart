package service;

import java.util.List;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> searchUsers(String searchText) {

        if (searchText == null || searchText.trim().isEmpty()) {
            return getAllUsers();
        }

        return userDAO.searchUsers(searchText.trim());
    }

    public String changeStatus(int id) {

        if (id <= 0) {
            return "Invalid user ID";
        }

        return userDAO.changeStatus(id);
    }

    public String deleteUser(int id) {

        if (id <= 0) {
            return "Invalid user ID";
        }

        return userDAO.deleteUser(id);
    }
}