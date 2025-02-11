package by.itacademy.jd2.staffaccountingspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private Long id;
    private String name;
    private String educationLevel;
    private double salary;
    private Long departmentId;
}
