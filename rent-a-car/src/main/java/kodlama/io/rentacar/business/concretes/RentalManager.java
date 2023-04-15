package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.abstracts.RentalService;
import kodlama.io.rentacar.business.dto.requests.create.invoice.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.rules.RentalBusinessRules;
import kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.business.dto.requests.create.rental.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.requests.update.rental.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.responses.create.rental.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.rental.UpdateRentalResponse;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final CarService carService;
    private final ModelMapper mapper;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final RentalBusinessRules rules;

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalResponse> responses = rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        rules.checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.checkIfCarAvailable(carService.getById(request.getCarId()).getState());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setStartDate(LocalDateTime.now());

        //CreatePayment
        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);

        repository.save(rental);
        carService.changeState(request.getCarId(), State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        //CreateInvoice
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, invoiceRequest);
        invoiceService.add(invoiceRequest);

        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRentalExists(id);
        int carId = repository.findById(id).orElseThrow().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id);
    }

    //BusinessRules
    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void createInvoiceRequest(CreateRentalRequest request, CreateInvoiceRequest invoiceRequest) {
        GetCarResponse car = carService.getById(request.getCarId());

        invoiceRequest.setCarId(request.getCarId());
        invoiceRequest.setModelName(car.getModelName());
        invoiceRequest.setBrandName(car.getModelBrandName());
        invoiceRequest.setDailyPrice(request.getDailyPrice());
        invoiceRequest.setPlate(car.getPlate());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setModelYear(car.getModelYear());
        invoiceRequest.setRentedForDays(request.getRentedForDays());
    }
}
