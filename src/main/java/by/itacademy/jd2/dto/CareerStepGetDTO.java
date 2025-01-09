package by.itacademy.jd2.dto;

import by.itacademy.jd2.entity.embedded.CareerStepId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerStepGetDTO {
    private CareerStepId id;
    private Date dateOfLiberationPosition;
    private String positionFullName;
    private String order;
}
