package by.itacademy.jd2.dto;

import by.itacademy.jd2.repository.embedded.Address;
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
    private String series;
    private String number;
    private String identificationNumber;
    private Address registrationAddress;
    private Date dateIssue;
    private Date dateEndAction;
    private String publisher;
}
