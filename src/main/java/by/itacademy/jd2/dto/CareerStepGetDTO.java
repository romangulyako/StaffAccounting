package by.itacademy.jd2.dto;

import by.itacademy.jd2.entity.embedded.CareerStepId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerStepGetDTO {
    private CareerStepId id;
    private String positionFullName;
    private String order;
}
