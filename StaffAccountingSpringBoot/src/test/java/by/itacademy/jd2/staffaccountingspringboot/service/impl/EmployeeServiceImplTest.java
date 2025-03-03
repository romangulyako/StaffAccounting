package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.MockConstant;
import by.itacademy.jd2.staffaccountingspringboot.MockUtils;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void cleanDatabase() {
        employeeRepository.deleteAll();
    }

    @Test
    void saveOrUpdateEmployee() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);

        assertNotNull(saved.getId());
        assertEquals(employee.getPersonData().getSurname(), saved.getPersonData().getSurname());
    }

    @Test
    void deleteEmployee() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);

        employeeService.deleteEmployee(saved.getId());

        assertFalse(employeeRepository.existsById(saved.getId()));
    }

    @Test
    void getEmployee() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);

        EmployeeDTO founded = employeeService.getEmployee(saved.getId());

        assertNotNull(founded);
        assertEquals(saved.getId(), founded.getId());
        assertEquals(saved.getPersonData().getSurname(), founded.getPersonData().getSurname());
    }

    @Test
    void getEmployeesPage() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);
        EmployeeFilterData filterData = MockUtils.createEmployeeFilterData();

        EmployeesPageDTO employeesPage = employeeService.getEmployeesPage(filterData,
                MockConstant.IS_FIRED,
                MockConstant.PAGE,
                MockConstant.SIZE);

        assertNotNull(employeesPage);
        assertFalse(employeesPage.getEmployees().isEmpty());
        assertEquals(MockConstant.LIST_SIZE, employeesPage.getEmployees().getContent().size());
    }

    @Test
    void returnToCurrent() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);

        EmployeeEntity entity = employeeRepository.findById(saved.getId()).orElseThrow();
        entity.setIsFired(!MockConstant.IS_FIRED);
        employeeRepository.save(entity);

        employeeService.returnToCurrent(saved.getId());
        EmployeeEntity updatedEntity = employeeRepository.findById(saved.getId()).orElseThrow();

        assertFalse(updatedEntity.getIsFired());
    }

    @Test
    void getCurrentEmployeeItems() {
        EmployeeDTO employee = MockUtils.createEmployeeDTO();
        EmployeeDTO saved = employeeService.saveOrUpdateEmployee(employee);

        List<EmployeeItemDTO> items = employeeService.getCurrentEmployeeItems();

        assertNotNull(items);
        assertFalse(items.isEmpty());
    }
}