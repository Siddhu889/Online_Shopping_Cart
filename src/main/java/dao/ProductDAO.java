
package dao;

import java.util.List;
import model.Product;

public interface ProductDAO {
	
	void addProduct(Product product);

    List<Product> getAllProducts();
    
    Product getProductById(int id);
    
    boolean updateProduct(Product product);
    
    List<Product> getProductsByCategory(int categoryId);
    
    List<Product> getLowStockProducts(int threshold);

    boolean deleteProduct(int id);
    
}
