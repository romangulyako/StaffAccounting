package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "education")
public class EducationEntity extends CommonSuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "education_level")
    private String educationLevel;

    @Column
    private String institution;

    @Column
    private String faculty;

    @Column
    private String specialization;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
