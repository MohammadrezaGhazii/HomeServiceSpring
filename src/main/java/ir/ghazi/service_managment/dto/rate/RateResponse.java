package ir.ghazi.service_managment.dto.rate;

import ir.ghazi.service_managment.model.Offer;

public record RateResponse(Long id,
                           Integer score,
                           String comment,
                           Offer offer) {
}
