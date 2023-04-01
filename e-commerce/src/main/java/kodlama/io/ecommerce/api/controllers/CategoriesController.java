package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.requests.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.update.product.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.category.UpdateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.update.product.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public List<GetAllCategoriesResponse> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse findCategoryById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
