package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.business.dto.requests.create.maintenance.CreateMaintenanceRequest;
import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Maintenance.NotExists);
    }
    public void checkIfCarUnderMaintenance(CreateMaintenanceRequest request) {
        if(repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId()))
            throw new BusinessException(Messages.Maintenance.CarNotExists);
    }
    public void checkIfCarIsNotUnderMaintenance(int carId) {
        if(!repository.existsByCarIdAndIsCompletedIsFalse(carId))
            throw new BusinessException(Messages.Maintenance.CarExists);
    }

    public void checkCarAvailabilityForMaintenance(State state) {
        if(state.equals(State.RENTED))
            throw new BusinessException(Messages.Maintenance.CarIsRented);
    }
}
