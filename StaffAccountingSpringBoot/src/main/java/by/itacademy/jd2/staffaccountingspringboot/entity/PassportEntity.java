package by.itacademy.jd2.staffaccountingspringboot.entity;

import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.Address;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@SuperBuilder
@Data
@ToString(exclude = "employee")
@EqualsAndHashCode(exclude = "employee", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passport")
public class PassportEntity extends BaseEntity {
    @Column(length = 2)
    private String series;

    @Column
    private String number;

    @Column(name = "identification_number")
    private String identificationNumber;

    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "registration_city")),
            @AttributeOverride(name = "street", column = @Column(name = "registration_street")),
            @AttributeOverride(name = "house", column = @Column(name = "registration_house")),
            @AttributeOverride(name = "apartment", column = @Column(name = "registration_apartment"))
    })
    @Embedded
    private Address registrationAddress;

    @Column(name = "date_issue")
    private Date dateIssue;

    @Column(name = "date_end_action")
    private Date dateEndAction;

    @Column
    private String publisher;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="employee_id", nullable = false, unique = true)
    private EmployeeEntity employee;
}
