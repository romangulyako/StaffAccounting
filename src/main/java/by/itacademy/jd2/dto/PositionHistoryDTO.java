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
    private Date dateOfLiberationPosition;
    private String order;
    private String employeeFullName;
}
