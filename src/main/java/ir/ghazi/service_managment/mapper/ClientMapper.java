package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.client.ClientRequest;
import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientSaveRequestToModel(ClientRequest request);

    ClientResponse modelToClientSaveResponse(Client client);
}
