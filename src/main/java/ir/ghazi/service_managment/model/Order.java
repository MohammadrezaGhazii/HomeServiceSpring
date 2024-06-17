package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import ir.ghazi.service_managment.enums.OrderSituation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "order_client")
public class Order extends BaseEntity<Long> {
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "requested_date")
    private LocalDate requestedDate;

    @Column(name = "requested_time")
    private LocalTime requestedTime;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "explain", nullable = false)
    private String explain;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_situation")
    private OrderSituation orderSituation;

    @ManyToOne
    @JoinColumn(name = "subservice_id")
    private SubService subService;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Offer> offers = new ArrayList<>();
}
