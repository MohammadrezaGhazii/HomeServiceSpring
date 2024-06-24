package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.offer.OfferRequest;
import ir.ghazi.service_managment.dto.offer.OfferResponse;
import ir.ghazi.service_managment.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer offerSaveRequestToModel(OfferRequest request);

    OfferResponse modelToOfferSaveResponse(Offer offer);
}
