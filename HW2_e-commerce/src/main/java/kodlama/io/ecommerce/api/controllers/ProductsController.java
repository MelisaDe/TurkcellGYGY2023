package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Product> findAll() {
        return service.getAll();
    }

    @GetMapping("/getById")
    public Product findProductById(@RequestParam int productId) {
        return service.getProductById(productId);
    }

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    @PatchMapping("/update")
    public ResponseEntity updateProduct(@RequestParam int productId, @RequestBody Product product) {
        return ResponseEntity.ok(service.updateProduct(productId, product));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteProduct(@RequestParam int productId) {
        return ResponseEntity.ok(service.deleteProduct(productId));
    }
}
