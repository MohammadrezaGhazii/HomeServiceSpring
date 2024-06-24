package ir.ghazi.service_managment.dto.order;

import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.SubService;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderRequest(LocalDate orderDate,

                           LocalDate requestedDate,

                           LocalTime requestedTime,

                           String address,

                           String explain,

                           OrderSituation orderSituation,

                           SubService subService,

                           Client client) {
}
