package ir.ghazi.service_managment.dto.exception;

import java.time.LocalDateTime;

public record ExceptionDto (String message,
                           LocalDateTime localDateTime){
}
