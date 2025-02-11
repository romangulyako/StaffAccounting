package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PassportEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                Converter.toEntity(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);

        LOGGER.info("Added employee with id={}", employeeEntity.getId());
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            EmployeeEntity newEmployee = Converter.toEntity(employeeDTO, EmployeeEntity.class);
            Optional<EmployeeEntity> oldEmployee = employeeRepository.findById(employeeDTO.getId());
            if (oldEmployee.isPresent()) {
                newEmployee.setPassport(oldEmployee.get().getPassport());
                newEmployee.setFired(oldEmployee.get().isFired());
                employeeRepository.save(newEmployee);

                LOGGER.info("Updated employee with id={}", newEmployee.getId());
            }
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

        LOGGER.info("Deleted employee with id={}", id);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).orElse(null);
        EmployeeDTO employeeDTO = Converter.toDto(entity, EmployeeDTO.class);
        if (employeeDTO != null) {
            LOGGER.info("Found employee with id={}", id);
        }

        return employeeDTO;
    }

    @Override
    public Page<EmployeeDTO> getEmployees(EmployeeFilterData filterData,
                                          Boolean isFired,
                                          Pageable pageable) {
        Page<EmployeeEntity> pageEntities = employeeRepository.findEmployeesByFilterData(filterData, isFired, pageable);

        LOGGER.info("Found {} from {} employees", pageEntities.stream().count(), pageEntities.getTotalElements());

        return pageEntities.map(entity -> Converter.toDto(entity, EmployeeDTO.class));
    }

    @Override
    public List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly) {
        List<EmployeeEntity> entities;
        if (isCurrentOnly) {
            entities = employeeRepository.findAllByIsFired(false);
        } else {
            entities = employeeRepository.findAll();
        }

        return entities.stream()
                .map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addPassport(PassportDTO passportDTO) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(passportDTO.getId());
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        if (employeeEntity.isPresent()) {
            employeeEntity.get().setPassport(passportEntity);
            passportEntity.setEmployee(employeeEntity.get());
            employeeRepository.save(employeeEntity.get());
            LOGGER.info("Added passport with id={}", passportEntity.getId());
        }
    }

    @Override
    public void updatePassport(PassportDTO passportDTO) {
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(passportEntity.getId());
        if (employeeEntity.isPresent()) {
            employeeEntity.get().setPassport(passportEntity);
            passportEntity.setEmployee(employeeEntity.get());
            employeeRepository.save(employeeEntity.get());
            LOGGER.info("Updated passport with id={}", passportEntity.getId());
        }
    }

    @Override
    public void deletePassport(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if (employeeEntity.isPresent()) {
            employeeEntity.get().setPassport(null);
            employeeRepository.save(employeeEntity.get());

            LOGGER.info("Deleted passport with id={}", id);
        }
    }

    @Override
    public PassportDTO getPassport(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if (employeeEntity.isPresent()) {
            PassportDTO passport =  Converter.toDto(employeeEntity.get().getPassport(), PassportDTO.class);
            if (passport != null) {
                LOGGER.info("Found passport with id={}", id);
            }

            return passport;
        }

        return null;
    }
}
