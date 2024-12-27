package by.itacademy.jd2.repository;

import by.itacademy.jd2.repository.embedded.Address;
import by.itacademy.jd2.repository.embedded.PersonData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity extends CommonSuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private boolean isFired;
}
