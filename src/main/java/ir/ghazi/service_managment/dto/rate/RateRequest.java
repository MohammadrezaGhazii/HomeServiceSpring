package ir.ghazi.service_managment.dto.rate;

import ir.ghazi.service_managment.model.Offer;

public record RateRequest(Integer score,
                          String comment,
                          Offer offer) {
}
