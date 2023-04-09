package kodlama.io.rentacar.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest { //base
    @NotBlank(message = "Kart numarası alanı boş bırakılamaz.")
    @Length(min = 16, max = 16, message = "Kart numarası 16 haneli olmalıdır.")
    private String cardNumber;
    @NotBlank(message = "Kart sahibi bilgisi boş bırakılamaz.")
    @Length(min = 5, message = "Kart sahibi bilgisi en az 5 karakter olmalıdır.")
    private String cardHolder;
    @NotNull(message = "Kartın son kullanma yılı boş bırakılamaz.")
    @Min(value = 2023, message = "Kartın son kullanma yılı geçersizdir.")
    private int cardExpirationYear;
    @NotNull(message = "Kartın son kullanma ayı boş bırakılamaz.")
    @Min(value = 1, message = "Kartın son kullanma ayı geçersizdir.")
    @Max(value = 12, message = "Kartın son kullanma ayı geçersizdir.")
    private int cardExpirationMonth;
    @NotBlank(message = "Kartın cvv bilgisi boş bırakılamaz.")
    @Length(min = 3, max = 3, message = "Kartın cvv bilgisi 3 haneli olmalıdır.")
    private String cardCvv;
}
