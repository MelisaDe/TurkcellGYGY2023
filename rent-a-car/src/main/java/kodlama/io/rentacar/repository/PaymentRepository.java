package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    boolean existByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv
            (String cardNumber, String cardHolder, int cardExpirationYear, int cardExpirationMonth, String cardCvv);

//    //Spel -> spring expression language
//    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
//            "FROM Payment p WHERE p.cardNumber = :#{#paymentRequest.cardNumber} " +
//            "AND p.cardHolder = :#{#paymentRequest.cardHolder} " +
//            "AND p.cardExpirationYear = :#{#paymentRequest.cardExpirationYear} " +
//            "AND p.cardExpirationMonth = :#{#paymentRequest.cardExpirationMonth} " +
//            "AND p.cardCvv = :#{#paymentRequest.cardCvv}")
//    boolean existByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv
//    (@Param("paymentRequest") CreatePaymentRequest paymentRequest);
}
