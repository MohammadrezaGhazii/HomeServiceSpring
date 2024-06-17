package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "service")
public class Services extends BaseEntity<Long> {
    @Column(name = "service_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<SubService> subServices = new ArrayList<>();

}
