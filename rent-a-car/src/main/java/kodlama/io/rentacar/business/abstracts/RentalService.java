package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.rental.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.requests.update.rental.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.responses.create.rental.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.rental.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}
