package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import kodlama.io.rentacar.repository.InvoiceRespository;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRespository respository;
    public void checkIfInvoiceExists(int id) {
        if (!respository.existsById(id)) {
            throw new BusinessException(Messages.Invoice.NotFound);
        }
    }
}
