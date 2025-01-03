package by.itacademy.jd2.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.sql.Date;

@Builder
@Data
@ToString(exclude = "employee")
@EqualsAndHashCode(exclude = "employee", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marital_status")
public class MaritalStatusEntity extends CommonSuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column
    private String document;

    @Column(name = "is_current")
    private boolean isCurrent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
