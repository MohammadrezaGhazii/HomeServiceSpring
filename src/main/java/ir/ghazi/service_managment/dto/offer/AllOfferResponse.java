package ir.ghazi.service_managment.dto.offer;

import ir.ghazi.service_managment.dto.order.AllOrdersResponse;
import ir.ghazi.service_managment.dto.specialist.FilterSpecialistResponse;
import ir.ghazi.service_managment.enums.OfferSituation;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;

import java.time.LocalDate;
import java.time.LocalTime;

public record AllOfferResponse(Long id,
                               LocalDate requestedDate,

                               LocalTime requestedTime,

                               Double offerPrice,

                               Double timeTodo,

                               OfferSituation offerSituation,

                               FilterSpecialistResponse specialist,

                               AllOrdersResponse order) {
}
