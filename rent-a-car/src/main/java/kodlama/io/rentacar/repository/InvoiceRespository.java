package kodlama.io.rentacar.repository;

import jakarta.transaction.Transactional;
import kodlama.io.rentacar.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRespository extends JpaRepository<Invoice, Integer> {
//    @Transactional
//    void deletedByRentalId(int id);
}
