package by.itacademy.jd2.utils;

import by.itacademy.jd2.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeUtils.class);
    private static EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeUtils(final EmployeeRepository employeeRepository) {
        EmployeeUtils.employeeRepository = employeeRepository;
    }

    /**
     * Проверяет наличие сотрудника в БД по его ID и если не находит, то пробрасывает {@code EntityNotFoundException}
     * @param id идентификатор сотрудника
     */
    public static void checkExistEmployee(final Long id) {
        if (!employeeRepository.existsById(id)) {
            LOGGER.warn(Constant.EMPLOYEE_NOT_FOUND, id);
            throw new EntityNotFoundException(LocaleUtils
                    .getMessage(Constant.EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id);
        }
    }
}
