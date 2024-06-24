package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.subservice.SubServiceRequest;
import ir.ghazi.service_managment.dto.subservice.SubServiceResponse;
import ir.ghazi.service_managment.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService subServiceSaveRequestToModel(SubServiceRequest request);

    SubServiceResponse modelToSubServiceSaveResponse(SubService subService);
}
