package dao;

import java.util.List;

import model.Cart;
import model.Cart_item;

public interface CartDAO {

	Cart getOrCreateCart(int customerId);
	
	boolean addToCart(Cart_item cartItem);
	
	List<Cart_item> getCartItems(int cartId);
	
	boolean updateCartItem(int cartItemId, int cartId, int quantity);
	
	boolean removeCartItem(int cartItemId, int cartId);
	
	double getCartTotal(int cartId);
	
}
