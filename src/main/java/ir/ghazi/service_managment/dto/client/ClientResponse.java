package ir.ghazi.service_managment.dto.client;

import java.time.LocalDate;

public record ClientResponse(Long id,
                             String firstName,
                             String lastName,
                             String email,
                             LocalDate registerDate) {
}
