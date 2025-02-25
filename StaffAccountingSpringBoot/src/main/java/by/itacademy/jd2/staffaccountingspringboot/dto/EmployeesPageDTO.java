package by.itacademy.jd2.staffaccountingspringboot.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
public class EmployeesPageDTO {
    private Page<EmployeeDTO> employees;
    private List<DepartmentItemDTO> departments;
}
