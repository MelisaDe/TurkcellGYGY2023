package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.model.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.model.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.model.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.model.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.model.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.model.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(int id, UpdateModelRequest request);
    void delete(int id);
}
