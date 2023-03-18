package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getProductById(int productId);

    Product createProduct(Product product);

    Product updateProduct(int productId, Product product);

    Product deleteProduct(int productId);
}
