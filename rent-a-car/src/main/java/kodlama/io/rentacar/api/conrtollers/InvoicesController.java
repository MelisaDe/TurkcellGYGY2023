package kodlama.io.rentacar.api.conrtollers;

import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.dto.requests.create.invoice.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.requests.update.invoice.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.responses.create.invoice.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetAllInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.invoice.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.update.invoice.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoiceResponse> findAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse findById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable int id, @RequestBody UpdateInvoiceRequest request){
        return  service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
