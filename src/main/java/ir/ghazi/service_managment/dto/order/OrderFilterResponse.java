package ir.ghazi.service_managment.dto.order;

import ir.ghazi.service_managment.enums.OrderSituation;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderFilterResponse(Long id,
                                  LocalDate orderDate,

                                  LocalDate requestedDate,

                                  LocalTime requestedTime,

                                  String address,

                                  String explain,

                                  OrderSituation orderSituation) {
}
