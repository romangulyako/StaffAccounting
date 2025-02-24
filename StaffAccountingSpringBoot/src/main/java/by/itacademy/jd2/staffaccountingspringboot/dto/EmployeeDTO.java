package by.itacademy.jd2.staffaccountingspringboot.dto;

import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.Address;
import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.PersonData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private PersonData personData;
    private Address homeAddress;
    private String phone;
    private String positionName;
}
