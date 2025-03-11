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
    @NotBlank(message = "{series_empty}")
    @Pattern(regexp = "[A-Z]{2}", message = "{series_pattern}")
    private String series;
    @NotBlank(message = "{number_empty}")
    @Pattern(regexp = "\\d{7}", message = "{number_pattern}")
    private String number;
    @NotBlank(message = "Identification number can't be empty")
    @Pattern(regexp = "\\d{7}[A-Z]\\d{3}[A-Z]{2}\\d",
            message = "{identification_pattern}")
    @Size(min = 14, max = 14, message = "{identification_length}")
    private String identificationNumber;
    private Address registrationAddress;
    private Date dateIssue;
    private Date dateEndAction;
    private String publisher;
    private Long employeeId;
}
