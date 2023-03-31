package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfProductExists(id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        checkIfProductExists(id);
        validateProduct(product);
        product.setId(id);
        return repository.save(product);
    }

    @Override
    public void delete(int id) {
        checkIfProductExists(id);
        repository.deleteById(id);
    }


    //BusinessRules
    private void checkIfProductExists(int id) {
        if(!repository.existsById(id)) throw new IllegalArgumentException("Böyle bir ürün mevcut değil.");
    }
    private void validateProduct(Product product){
        checkIfUnitePriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionValid(product);
    }
    private void checkIfDescriptionValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50) {
            throw new IllegalArgumentException("Geçerli ürün açıklaması giriniz. Ürün açıklaması 10-50 karakter arası olmalıdır.");
        }
    }
    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Geçerli ürün miktarı giriniz. Ürün miktarı 0'dan büyük olmalıdır.");
        }
    }
    private void checkIfUnitePriceValid(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Geçerli ürün fiyatı giriniz. Ürünün fiyatı 0 tl'den büyük olmalıdır.");
        }
    }
}
