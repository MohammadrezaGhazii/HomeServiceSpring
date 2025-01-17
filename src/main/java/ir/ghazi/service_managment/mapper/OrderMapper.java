package ir.ghazi.service_managment.mapper;

import ir.ghazi.service_managment.dto.order.AllOrdersResponse;
import ir.ghazi.service_managment.dto.order.OrderFilterResponse;
import ir.ghazi.service_managment.dto.order.OrderRequest;
import ir.ghazi.service_managment.dto.order.OrderResponse;
import ir.ghazi.service_managment.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderSaveRequestToModel(OrderRequest request);

    OrderResponse modelToOrderSaveResponse(Order order);

    OrderFilterResponse modelToOrderFilterResponse(Order order);

    AllOrdersResponse modelToAllOrdersResponse(Order order);
}
