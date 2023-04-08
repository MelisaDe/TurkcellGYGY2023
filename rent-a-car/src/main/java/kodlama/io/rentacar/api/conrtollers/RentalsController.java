package kodlama.io.rentacar.api.conrtollers;

import kodlama.io.rentacar.business.abstracts.RentalService;
import kodlama.io.rentacar.business.dto.requests.create.rental.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.requests.update.rental.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.responses.create.rental.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.rental.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/rentals")
public class RentalsController {
    private final RentalService service;

    @GetMapping
    public List<GetAllRentalResponse> getAll(){
        return  service.getAll();
    }
    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return  service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request){
        return  service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
