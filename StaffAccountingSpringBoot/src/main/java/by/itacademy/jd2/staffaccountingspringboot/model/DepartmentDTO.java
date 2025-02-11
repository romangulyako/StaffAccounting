package by.itacademy.jd2.staffaccountingspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    private String genitiveCaseName;
    private String description;
    private Integer actualPositionsCount;
    private Integer reducedPositionsCount;
}
