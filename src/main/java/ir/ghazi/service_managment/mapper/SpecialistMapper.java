package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.specialist.SpecialistRequest;
import ir.ghazi.service_managment.dto.specialist.SpecialistResponse;
import ir.ghazi.service_managment.model.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecialistMapper {
    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    Specialist specialistSaveRequestToModel(SpecialistRequest request);

    SpecialistResponse modelToSpecialistSaveResponse(Specialist specialist);
}
