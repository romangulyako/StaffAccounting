package by.itacademy.jd2.entity;

import by.itacademy.jd2.entity.embedded.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@SuperBuilder
@Data
@ToString(exclude = "employee")
@EqualsAndHashCode(exclude = "employee", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passport")
public class PassportEntity extends CommonSuperEntity {
    @Id
    @GenericGenerator(name = "one-one",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property",
                    value = "employee"))
    @GeneratedValue(generator = "one-one")
    @Column(name = "EMPLOYEE_ID")
    @Access(AccessType.PROPERTY)
    private Long id;

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

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private EmployeeEntity employee;
}
