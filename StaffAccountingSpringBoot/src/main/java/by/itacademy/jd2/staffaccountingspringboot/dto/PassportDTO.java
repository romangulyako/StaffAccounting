package by.itacademy.jd2.staffaccountingspringboot.dto;

import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportDTO {
    private Long id;
    @NotBlank(message = "Series can't be empty")
    @Pattern(regexp = "[A-Z]{2}", message = "The series should contain only 2 capital Latin letters")
    private String series;
    @NotBlank(message = "Number can't be empty")
    @Pattern(regexp = "\\d{7}", message = "Number should contain only seven digits")
    private String number;
    @NotBlank(message = "Identification number can't be empty")
    @Pattern(regexp = "\\d{7}[A-Z]\\d{3}[A-Z]{2}\\d",
            message = "The identification number does not correspond to the template")
    @Size(min = 14, max = 14, message = "The length of the identification number should be 14 characters")
    private String identificationNumber;
    private Address registrationAddress;
    private Date dateIssue;
    private Date dateEndAction;
    private String publisher;
    private Long employeeId;
}
