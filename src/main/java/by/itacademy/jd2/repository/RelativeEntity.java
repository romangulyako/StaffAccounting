package by.itacademy.jd2.repository;

import by.itacademy.jd2.repository.embedded.PersonData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@Data
@ToString(exclude = "employee")
@EqualsAndHashCode(exclude = "employee", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relatives")
public class RelativeEntity extends CommonSuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonData personData;

    @Column(name = "type_kinship")
    private String typeKinship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
