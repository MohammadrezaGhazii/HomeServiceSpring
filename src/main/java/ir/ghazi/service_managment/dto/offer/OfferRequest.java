package ir.ghazi.service_managment.dto.offer;

import ir.ghazi.service_managment.enums.OfferSituation;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;

import java.time.LocalDate;
import java.time.LocalTime;

public record OfferRequest(LocalDate requestedDate,

                           LocalTime requestedTime,

                           Double offerPrice,

                           Double timeTodo,

                           OfferSituation offerSituation,

                           Specialist specialist,

                           Order order) {
}
