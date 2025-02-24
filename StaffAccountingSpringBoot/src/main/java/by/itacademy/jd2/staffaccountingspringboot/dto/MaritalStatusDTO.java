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
public class MaritalStatusDTO {
    private Long id;
    private String status;
    private Date registrationDate;
    private String document;
    private Long employeeId;
}
