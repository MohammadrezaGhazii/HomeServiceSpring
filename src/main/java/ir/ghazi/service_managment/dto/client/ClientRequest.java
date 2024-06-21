package ir.ghazi.service_managment.dto.client;

import java.time.LocalDate;

public record ClientRequest(String firstName,
                            String lastName,
                            String email,
                            String password,
                            LocalDate registerDate) {
}
