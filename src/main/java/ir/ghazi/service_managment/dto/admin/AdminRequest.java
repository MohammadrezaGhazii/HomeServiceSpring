package ir.ghazi.service_managment.dto.admin;

import java.time.LocalDate;

public record AdminRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate registerDate){
}
