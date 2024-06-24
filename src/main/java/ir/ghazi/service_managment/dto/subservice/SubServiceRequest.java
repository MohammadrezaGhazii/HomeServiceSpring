package ir.ghazi.service_managment.dto.subservice;

import ir.ghazi.service_managment.model.Services;

public record SubServiceRequest(String name,
                                Double basePrice,
                                String description,
                                Services service) {
}
