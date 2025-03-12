package by.itacademy.jd2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionHistoryDTO {
    private Date dateOfAppointment;
    private String orderAppointment;
    private Date dateOfLiberation;
    private String orderLiberation;
    private String employeeSurname;
    private String employeeName;
    private String employeePatronymic;
}
