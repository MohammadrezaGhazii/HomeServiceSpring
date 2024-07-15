package ir.ghazi.service_managment.model;

import ir.ghazi.service_managment.base.entity.BaseEntity;
import ir.ghazi.service_managment.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@MappedSuperclass
public class Person extends BaseEntity<Long> {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Enumerated(EnumType.STRING)
    private Role role;
}
