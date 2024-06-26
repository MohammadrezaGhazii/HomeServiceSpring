package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.rate.RateResponse;
import ir.ghazi.service_managment.model.Rate;
import ir.ghazi.service_managment.repository.RateRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper {
    RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);

    Rate rateSaveRequestToModel(RateRepository request);

    RateResponse modelToRateSaveResponse(Rate rate);
}
