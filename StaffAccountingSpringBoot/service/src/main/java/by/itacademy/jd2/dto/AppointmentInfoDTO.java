package by.itacademy.jd2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentInfoDTO {
    private List<EmployeeItemDTO> employees;
    private List<PositionItemDTO> positions;
}
