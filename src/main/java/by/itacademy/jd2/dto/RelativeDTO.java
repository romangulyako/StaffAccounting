package by.itacademy.jd2.dto;

import by.itacademy.jd2.repository.embedded.PersonData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelativeDTO {
    private Long id;
    private PersonData personData;
    private String typeKinship;
    private Long employeeId;
}
