package ir.ghazi.service_managment.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private String cardNumber;
    private String cvv;
    private String month;
    private String year;
    private String password;
}
