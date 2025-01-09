package by.itacademy.jd2.entity;

import by.itacademy.jd2.entity.embedded.Address;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Builder
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
