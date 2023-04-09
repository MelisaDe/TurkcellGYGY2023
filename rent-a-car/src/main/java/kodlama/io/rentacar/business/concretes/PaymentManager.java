package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.core.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.business.dto.requests.create.payment.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.requests.update.payment.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.responses.create.payment.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetAllPaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.rental.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.payment.UpdatePaymentResponse;
import kodlama.io.rentacar.entities.Payment;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllPaymentResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentResponse> responses = payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        checkIfCardExists(request);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        checkIfBalanceEnough(request.getPrice(), payment.getBalance());
        //fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }

    //BusinessRules
    private void checkIfPaymentExists(int id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Ödeme bilgisi bulunamadı.");
        }
    }
    private void checkIfBalanceEnough(double price, double balance) {
        if(balance < price) {
            throw new RuntimeException("Yetersiz bakiye.");
        }
    }

    private void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if(!repository.existByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv())) {
            throw new RuntimeException("Kart bilgileri hatalıdır.");
        }
    }

    private void checkIfCardExists(CreatePaymentRequest request) {
        if(repository.existsByCardNumber(request.getCardNumber())) {
            throw new RuntimeException("Kart numarası zaten kayıtlı.");
        }
    }
}
