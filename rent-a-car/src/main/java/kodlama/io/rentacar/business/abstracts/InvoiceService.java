package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.invoice.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.requests.update.invoice.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.responses.create.invoice.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetAllInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.update.invoice.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
