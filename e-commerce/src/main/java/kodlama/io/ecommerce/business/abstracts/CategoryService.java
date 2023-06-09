package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.responses.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.update.category.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(int id);
    CreateCategoryResponse add(CreateCategoryRequest request);
    UpdateCategoryResponse update(int id, UpdateCategoryRequest request);
    void delete(int id);
}
