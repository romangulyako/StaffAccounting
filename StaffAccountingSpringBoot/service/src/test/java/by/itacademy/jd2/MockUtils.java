package by.itacademy.jd2;

import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.repository.custom.EmployeeFilterData;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.entity.embedded.PersonData;

public class MockUtils {
    public static EmployeeDTO createEmployeeDTO() {
        return EmployeeDTO.builder()
                .personData(PersonData.builder()
                        .surname(MockConstant.SURNAME)
                        .name(MockConstant.NAME)
                        .patronymic(MockConstant.PATRONYMIC)
                        .birthday(MockConstant.BIRTHDAY)
                        .build())
                .homeAddress(Address.builder()
                        .city(MockConstant.CITY)
                        .street(MockConstant.STREET)
                        .house(MockConstant.HOUSE)
                        .build())
                .build();
    }

    public static EmployeeFilterData createEmployeeFilterData() {
        return EmployeeFilterData.builder()
                .name(MockConstant.NAME)
                .build();
    }
}
