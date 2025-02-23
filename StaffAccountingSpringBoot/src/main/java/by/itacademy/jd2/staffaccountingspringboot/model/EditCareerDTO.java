package by.itacademy.jd2.staffaccountingspringboot.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EditCareerDTO {
    private CareerStepGetDTO careerStep;
    private List<PositionItemDTO> positions;
}
