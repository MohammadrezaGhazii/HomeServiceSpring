package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.service.ServiceRequest;
import ir.ghazi.service_managment.dto.service.ServiceResponse;
import ir.ghazi.service_managment.model.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    Services serviceSaveRequestToModel(ServiceRequest request);

    ServiceResponse modelToServiceSaveResponse(Services services);
}
