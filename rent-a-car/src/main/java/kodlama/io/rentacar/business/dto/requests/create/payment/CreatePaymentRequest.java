package kodlama.io.rentacar.business.dto.requests.create.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    @NotNull(message = "Bakiye boş bırakılamaz.")
    @Min(value = 1, message = "Bakiye 1'dan küçük olamaz.")
    private double balance;
}


