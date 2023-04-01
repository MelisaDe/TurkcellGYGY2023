package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.requests.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.product.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.product.UpdateProductResponse;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.Status;
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
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = repository.findAll();
        List<GetAllProductsResponse> responses = products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();

        return responses;

    }

    @Override
    public GetProductResponse getById(int id) {
        checkIfProductExistsById(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = mapper.map(product, GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        checkIfProductExistsByName(request.getName());
        Product product = mapper.map(request, Product.class);
        product.setId(0);
        product.setStatus(Status.AVAILABLE);
        validateProduct(product);
        repository.save(product);
        CreateProductResponse response = mapper.map(product, CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        checkIfProductExistsById(id);
        Product product = mapper.map(request, Product.class);
        product.setId(id);
        validateProduct(product);
        repository.save(product);
        UpdateProductResponse response = mapper.map(product, UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfProductExistsById(id);
        repository.deleteById(id);
    }


    //BusinessRules
    private void checkIfProductExistsById(int id) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Böyle bir ürün mevcut değil.");
    }
    private void checkIfProductExistsByName(String name){
        if (repository.existsByNameIgnoreCase(name))
            throw new IllegalArgumentException("Bu ürün zaten mevcut.");
    }

    private void validateProduct(Product product) {
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
