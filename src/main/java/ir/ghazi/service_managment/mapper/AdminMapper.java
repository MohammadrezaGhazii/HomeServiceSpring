package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.admin.AdminRequest;
import ir.ghazi.service_managment.dto.admin.AdminResponse;
import ir.ghazi.service_managment.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin adminSaveRequestToModel(AdminRequest request);

    AdminResponse modelToUserSaveResponse(Admin admin);
}
