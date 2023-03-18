package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        if (repository.getAll().size() == 0 || repository.getAll() == null)
            throw new RuntimeException("Ürün bulunamadı.");

        return repository.getAll();
    }

    @Override
    public Product getProductById(int productId) {
        return repository.getProductById(productId);
    }

    @Override
    public Product createProduct(Product product) {
        controlProduct(product);
        return repository.createProduct(product);
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        controlProduct(product);
        return repository.updateProduct(productId, product);
    }

    @Override
    public Product deleteProduct(int productId) {
        return repository.deleteProduct(productId);
    }

    private void controlProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Geçerli ürün fiyatı giriniz. Ürünün fiyatı 0 tl'den büyük olmalıdır.");
        }
        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Geçerli ürün miktarı giriniz. Ürün miktarı 0'dan büyük olmalıdır.");
        }
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            throw new RuntimeException("Geçerli ürün açıklaması giriniz. Ürün açıklaması 10-50 karakter arası olmalıdır.");
        }
    }
}
