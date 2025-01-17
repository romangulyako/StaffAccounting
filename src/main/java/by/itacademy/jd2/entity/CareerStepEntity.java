package by.itacademy.jd2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career")
public class CareerStepEntity extends CommonSuperEntityWithId {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PositionEntity position;

    @Column(name = "date_of_appointment")
    private Date dateOfAppointment;

    @Column(name = "order_appointment")
    private String orderAppointment;

    @Column(name = "date_of_liberation_position")
    private Date dateOfLiberationPosition;

    @Column(name = "order_liberation")
    private String orderLiberation;

    @Column(name = "is_current")
    @Builder.Default
    private boolean isCurrent = true;
}
