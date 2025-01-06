package by.itacademy.jd2.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerStepId implements Serializable {
    private Long employee;
    private Long position;
    private Date dateOfAppointment;
}
