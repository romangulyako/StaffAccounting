package by.itacademy.jd2.staffaccountingspringboot.utils;

import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeUtils.class);
    private static final String EMPLOYEE_NOT_FOUND_EXCEPTION = "Employee not found. ID=";
    private static EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeUtils(final EmployeeRepository employeeRepository) {
        EmployeeUtils.employeeRepository = employeeRepository;
    }

    public static EmployeeEntity findById(final Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn(Constant.EMPLOYEE_NOT_FOUND, id);
                    return new EntityNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION + id);
                });
    }
}
