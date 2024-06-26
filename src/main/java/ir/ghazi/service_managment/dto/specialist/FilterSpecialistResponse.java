package ir.ghazi.service_managment.dto.specialist;

import ir.ghazi.service_managment.enums.SpecialistSituation;

import java.time.LocalDate;

public record FilterSpecialistResponse(Long id,
                                       String firstName,
                                       String lastName,
                                       String email,
                                       LocalDate registerDate,
                                       SpecialistSituation situation,
                                       Double score) {
}
