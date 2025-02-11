package by.itacademy.jd2.staffaccountingspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DismissDTO {
    private Long employeeId;
    private Date dateOfDismiss;
    private String orderDismiss;
}
