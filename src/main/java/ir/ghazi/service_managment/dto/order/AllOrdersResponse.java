package ir.ghazi.service_managment.dto.order;

import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.dto.subservice.SubServiceResponse;
import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.SubService;

import java.time.LocalDate;
import java.time.LocalTime;

public record AllOrdersResponse(Long id,
                                LocalDate orderDate,

                                LocalDate requestedDate,

                                LocalTime requestedTime,

                                String address,

                                String explain,

                                OrderSituation orderSituation,

                                SubServiceResponse subService,

                                ClientResponse client) {
}
