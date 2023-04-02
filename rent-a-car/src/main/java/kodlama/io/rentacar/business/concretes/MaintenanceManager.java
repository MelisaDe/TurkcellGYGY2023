package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.maintenance.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.car.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.maintenance.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.maintenance.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.maintenance.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> responses = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        GetCarResponse carResponse = carService.getById(request.getCarId());
        checkIfCarAvailable(carResponse.getState());

        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);

        carResponse.setState(State.MAINTENANCE);
        carService.update(carResponse.getId(), mapper.map(carResponse, UpdateCarRequest.class));

        repository.save(maintenance);

        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);
        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        GetCarResponse carResponse = carService.getById(request.getCarId());

        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);

        carResponse.setState(State.AVAILABLE);
        carService.update(carResponse.getId(), mapper.map(carResponse, UpdateCarRequest.class));

        repository.save(maintenance);

        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        repository.deleteById(id);
    }

    //BusinessRules
    private void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("There is no such a maintenance!");
    }

    private void checkIfCarAvailable(State state) {
        if (state != State.AVAILABLE) throw new IllegalArgumentException("The car is not available!");
    }

}
