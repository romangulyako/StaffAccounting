package by.itacademy.jd2.staffaccountingspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerStepGetDTO {
    private Long id;
    private Long employeeId;
    private Long positionId;
    private Date dateOfAppointment;
    private String orderAppointment;
    private Date dateOfLiberation;
    private String orderLiberation;
    private String positionName;
    private String departmentGenitiveCaseName;
}
