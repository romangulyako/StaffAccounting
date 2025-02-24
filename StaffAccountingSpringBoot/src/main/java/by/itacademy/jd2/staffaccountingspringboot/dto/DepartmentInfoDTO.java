package by.itacademy.jd2.staffaccountingspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInfoDTO {
    private DepartmentDTO department;
    private List<PositionDTO> positions;
    private int page;
    private int size;
    private int totalPages;
    private Boolean isActual;
}
