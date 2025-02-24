package by.itacademy.jd2.staffaccountingspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilterData {
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private Long departmentId;
}
