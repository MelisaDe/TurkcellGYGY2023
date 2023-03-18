package kodlama.io.ecommerce.repository.concretes;

import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    List<Product> products;

    public InMemoryProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1, "test1", 10, 11, "test1test1"));
        products.add(new Product(2, "test2", 20, 22, "test2test2"));
        products.add(new Product(3, "test3", 30, 33, "test3test3"));
        products.add(new Product(4, "test4", 40, 44, "test4test4"));
        products.add(new Product(5, "test5", 50, 55, "test5test5"));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product p : products) {
            if (p.getId() == productId)
                return p;
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        int index = 0;
        for (Product p : products) {
            if (p.getId() == productId) {
                products.set(index, product);
                break;
            }
            index++;
        }
        return products.get(index);
    }

    @Override
    public Product deleteProduct(int productId) {
        for (Product p : products) {
            if (p.getId() == productId) {
                products.remove(p);
                return p;
            }
        }
        return null;
    }
}
