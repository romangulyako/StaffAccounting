package by.itacademy.jd2.staffaccountingspringboot.entity;

import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.Address;
import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.PersonData;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@SuperBuilder
@Data
@EqualsAndHashCode(exclude = "career", callSuper = false)
@ToString(exclude = "career")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity extends CommonSuperEntity {
    @Embedded
    private PersonData personData;

    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "residence_city")),
            @AttributeOverride(name = "street", column = @Column(name = "residence_street")),
            @AttributeOverride(name = "house", column = @Column(name = "residence_house")),
            @AttributeOverride(name = "apartment", column = @Column(name = "residence_apartment"))
    })
    @Embedded
    private Address homeAddress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_fired")
    @Builder.Default
    private Boolean isFired = false;

    @OneToOne(mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private PassportEntity passport;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<RelativeEntity> relatives = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "employee")
    @Builder.Default
    private Set<MaritalStatusEntity> maritalStatuses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<EducationEntity> educations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "employee")
    @Builder.Default
    private Set<CareerStepEntity> career = new HashSet<>();
}
