package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.business.dto.requests.create.payment.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.requests.update.payment.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.responses.create.payment.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetAllPaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.responses.update.payment.UpdatePaymentResponse;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}
