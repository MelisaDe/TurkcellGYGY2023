package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
}
