package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import ir.ghazi.service_managment.enums.OfferSituation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "offer")
public class Offer extends BaseEntity<Long> {
    @Column(name = "offer_date")
    private LocalDate requestedDate;

    @Column(name = "offer_time")
    private LocalTime requestedTime;

    @Column(name = "offer_price")
    private Double offerPrice;

    @Column(name = "time_todo")
    private Double timeTodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_situation")
    private OfferSituation offerSituation;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
