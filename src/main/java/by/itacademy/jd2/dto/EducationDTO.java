package by.itacademy.jd2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {
    private Long id;
    private String educationLevel;
    private String institution;
    private String faculty;
    private String specialization;
    private Date dateStart;
    private Date dateEnd;
    private Long employeeId;
}
