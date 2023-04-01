package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.requests.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.responses.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.update.category.UpdateCategoryResponse;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> categories = repository.findAll();
        List<GetAllCategoriesResponse> responses = categories
                .stream()
                .map(category -> mapper.map(category, GetAllCategoriesResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        checkIfCategoryExistsById(id);
        Category category = repository.findById(id).orElseThrow();
        GetCategoryResponse response = mapper.map(category, GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        checkIfCategoryExistsByName(request.getName());
        Category category = mapper.map(request, Category.class);
        category.setId(0);
        repository.save(category);
        CreateCategoryResponse response = mapper.map(category, CreateCategoryResponse.class);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(int id, UpdateCategoryRequest request) {
        checkIfCategoryExistsById(id);
        Category category = mapper.map(request, Category.class);
        category.setId(id);
        repository.save(category);
        UpdateCategoryResponse response = mapper.map(category, UpdateCategoryResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfCategoryExistsById(id);
        repository.deleteById(id);
    }

    //BusinessRules
    private void checkIfCategoryExistsById(int id) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Böyle bir kategori mevcut değil.");
    }
    private void checkIfCategoryExistsByName(String name){
        if (repository.existsByNameIgnoreCase(name))
            throw new IllegalArgumentException("Bu kategori zaten mevcut.");
    }
}
