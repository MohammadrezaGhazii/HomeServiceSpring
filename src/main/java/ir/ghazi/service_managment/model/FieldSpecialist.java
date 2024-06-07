package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

@Table
@Entity(name = "field_specialist")
public class FieldSpecialist extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "sub_service_id")
    private SubService subService;
}
