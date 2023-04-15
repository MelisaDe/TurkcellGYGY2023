package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.dto.requests.create.invoice.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.requests.update.invoice.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.responses.create.invoice.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.car.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetAllInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.update.invoice.UpdateInvoiceResponse;
import kodlama.io.rentacar.entities.Invoice;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.repository.InvoiceRespository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRespository respository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllInvoiceResponse> getAll() {
        List<Invoice> invoices = respository.findAll();
        List<GetAllInvoiceResponse> responses = invoices
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoiceResponse.class))
                .toList();
        return responses;

    }

    @Override
    public GetInvoiceResponse getById(int id) {
        checkIfInvoiceExists(id);
        Invoice invoice = respository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setRentedAt(LocalDateTime.now());
        invoice.setTotalPrice(getTotalPrice(invoice));
        respository.save(invoice);
        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        checkIfInvoiceExists(id);
        Invoice invoice = respository.findById(id).orElseThrow();
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        respository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfInvoiceExists(id);
        respository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }

    private void checkIfInvoiceExists(int id) {
        if (!respository.existsById(id)) {
            throw new RuntimeException("Fatura bulunamadı.");
        }
    }
}
