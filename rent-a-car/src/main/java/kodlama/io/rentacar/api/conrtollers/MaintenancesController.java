package kodlama.io.rentacar.api.conrtollers;

import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.maintenance.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.maintenance.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.maintenance.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.maintenance.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@AllArgsConstructor
public class MaintenancesController {
    private final MaintenanceService service;
    @GetMapping
    List<GetAllMaintenancesResponse> getAll(){
        return  service.getAll();
    }
    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id){
        return  service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request){
        return  service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
