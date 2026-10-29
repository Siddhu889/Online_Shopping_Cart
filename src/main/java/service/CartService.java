
package service;

import java.util.List;

import dao.CartDAO;
import model.Cart;
import model.Cart_item;

public class CartService {

    private CartDAO cartDAO;

    // Connect Service with DAO
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    // Get existing cart or create a new cart
    public Cart getOrCreateCart(int customerId) {

        if (customerId <= 0) {
            return null;
        }

        return cartDAO.getOrCreateCart(customerId);
    }

    // Add product to cart
    public boolean addToCart(Cart_item cartItem) {

        if (cartItem == null) {
            return false;
        }

        if (cartItem.getCart_id() <= 0 ||
            cartItem.getProduct_id() <= 0 ||
            cartItem.getQuantity() <= 0) {
            return false;
        }

        return cartDAO.addToCart(cartItem);
    }

    // Get all items in cart
    public List<Cart_item> getCartItems(int cartId) {

        if (cartId <= 0) {
            return new java.util.ArrayList<>();
        }

        return cartDAO.getCartItems(cartId);
    }

    // Update cart item quantity
    public boolean updateCart(int itemId, int cartId, int quantity) {

        if (itemId <= 0 || cartId <= 0 || quantity <= 0) {
            return false;
        }

        return cartDAO.updateCartItem(itemId, cartId, quantity);
    }

    // Remove cart item
    public boolean removeFromCart(int itemId, int cartId) {

        if (itemId <= 0 || cartId <= 0) {
            return false;
        }

        return cartDAO.removeCartItem(itemId, cartId);
    }

    // Calculate total amount
    public double getCartTotal(int cartId) {

        if (cartId <= 0) {
            return 0;
        }

        return cartDAO.getCartTotal(cartId);
    }
}
