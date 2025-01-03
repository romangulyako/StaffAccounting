package by.itacademy.jd2.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonData {
    private String surname;
    private String name;
    private String patronymic;
    private Date birthday;
}
