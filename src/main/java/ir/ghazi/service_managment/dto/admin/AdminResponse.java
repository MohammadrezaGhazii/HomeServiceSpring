package ir.ghazi.service_managment.dto.admin;

import java.time.LocalDate;

public record AdminResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate registerDate
) {
}
