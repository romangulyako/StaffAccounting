package by.itacademy.jd2.entity;

import by.itacademy.jd2.entity.embedded.CareerStepId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career")
@IdClass(CareerStepId.class)
public class CareerStepEntity extends CommonSuperEntity {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity employee;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PositionEntity position;

    @Id
    @Column(name = "date_of_appointment")
    private Date dateOfAppointment;

    @Column(name = "order_number")
    private String order;

    @Column(name = "is_current")
    private boolean isCurrent;
}
