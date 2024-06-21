package ir.ghazi.service_managment.dto.specialist;

import ir.ghazi.service_managment.enums.SpecialistSituation;
import jakarta.persistence.Column;
import lombok.ToString;

import java.time.LocalDate;

public record SpecialistRequest(String firstName,
                                String lastName,
                                String email,
                                String password,
                                LocalDate registerDate,
                                SpecialistSituation situation,
                                byte[] image,
                                Double score) {
}
