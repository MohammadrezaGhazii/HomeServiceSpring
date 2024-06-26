package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.rate.RateRequest;
import ir.ghazi.service_managment.dto.rate.RateResponse;
import ir.ghazi.service_managment.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper {
    RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);

    Rate rateSaveRequestToModel(RateRequest request);

    RateResponse modelToRateSaveResponse(Rate rate);
}
