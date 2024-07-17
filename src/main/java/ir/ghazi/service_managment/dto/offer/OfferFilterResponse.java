package ir.ghazi.service_managment.dto.offer;

import ir.ghazi.service_managment.enums.OfferSituation;
import ir.ghazi.service_managment.model.Order;

import java.time.LocalDate;
import java.time.LocalTime;

public record OfferFilterResponse(Long id,
                                  LocalDate requestedDate,

                                  LocalTime requestedTime,

                                  Double offerPrice,

                                  Double timeTodo,

                                  OfferSituation offerSituation) {
}
