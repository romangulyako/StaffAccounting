package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.RelativeEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.RelativeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RelativeServiceImpl implements RelativeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelativeServiceImpl.class);
    private final RelativeRepository relativeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void addRelative(RelativeDTO relativeDTO) {
        if (relativeDTO != null) {
            RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
            EmployeeEntity employeeEntity = employeeRepository.findById(relativeDTO.getEmployeeId()).orElse(null);
            if (employeeEntity != null) {
                relativeEntity.setEmployee(employeeEntity);
                employeeEntity.getRelatives().add(relativeEntity);
                relativeRepository.save(relativeEntity);

                LOGGER.info("Added relative with id={} for employee with id={}",
                        relativeEntity.getId(), employeeEntity.getId());
            }
        }
    }

    @Override
    public void updateRelative(RelativeDTO relativeDTO) {
        if (relativeDTO != null) {
            RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
            EmployeeEntity employeeEntity = employeeRepository.findById(relativeDTO.getEmployeeId()).orElse(null);
            if (employeeEntity != null) {
                relativeEntity.setEmployee(employeeEntity);
                relativeRepository.save(relativeEntity);

                LOGGER.info("Updated relative with id={}", relativeEntity.getId());
            }
        }
    }

    @Override
    public void deleteRelative(Long id) {
        RelativeEntity relativeEntity = relativeRepository.findById(id).orElse(null);
        if (relativeEntity != null) {
            relativeRepository.delete(relativeEntity);
            LOGGER.info("Deleted relative with id={}", id);
        }
    }

    @Override
    public RelativeDTO getRelative(Long id) {
        RelativeEntity relativeEntity = relativeRepository.findById(id).orElse(null);
        if (relativeEntity != null) {
            LOGGER.info("Found relative with id={}", id);
        }

        return Converter.toDto(relativeEntity, RelativeDTO.class);
    }

    @Override
    public Page<RelativeDTO> getRelatives(Long employeeId, Pageable pageable) {
        Page<RelativeEntity> entities = relativeRepository.findAllByEmployeeId(employeeId, pageable);
        LOGGER.info("Found {} from {} relatives", entities.stream().count(), entities.getTotalElements());
        return entities.map(entity -> Converter.toDto(entity, RelativeDTO.class));
    }
}
