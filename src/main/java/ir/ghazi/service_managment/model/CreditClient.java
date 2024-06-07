package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "credit_client")
public class CreditClient extends BaseEntity<Long> {
    @Column(name = "inventory")
    private double inventory;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
