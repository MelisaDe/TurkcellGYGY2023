package kodlama.io.rentacar.api.conrtollers;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.model.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.model.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.model.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.model.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.model.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.model.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> findAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse findModelById(@PathVariable int id) {

        return service.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
