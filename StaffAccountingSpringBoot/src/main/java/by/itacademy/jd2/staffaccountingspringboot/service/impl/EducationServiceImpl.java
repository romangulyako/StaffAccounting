package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EducationEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EducationRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
import jakarta.persistence.EntityNotFoundException;
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
public class EducationServiceImpl implements EducationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);
    private final EducationRepository educationRepository;
    @Override
    public void saveOrUpdateEducation(EducationDTO educationDTO) {
        EducationEntity entity = Converter.toEntity(educationDTO, EducationEntity.class);
        educationRepository.save(entity);
        LOGGER.info("Education saved successfully. ID={}", entity.getId());
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
        LOGGER.info("Education with id={} deleted successfully", id);
    }

    @Override
    public EducationDTO getEducation(Long id) {
        EducationEntity educationEntity = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info("Education with id={} not found", id);
                    return new EntityNotFoundException("Education with id " + id + " not found");
                });
        LOGGER.info("Successfully fetched education with id={} from database", id);
        return  Converter.toDto(educationEntity, EducationDTO.class);
    }

    @Override
    public Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, Pageable pageable) {
        Page<EducationEntity> entities = educationRepository.findAllByEmployeeIdOrderByDateEndAsc(employeeId, pageable);
        if (entities.getContent().isEmpty()) {
            LOGGER.warn("No education found for employee ID={} for the provided parameters: page={}, size={}",
                    employeeId, pageable.getPageNumber(), pageable.getPageSize());
        } else {
            LOGGER.info("Successfully fetched {} education rows from the database", entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, EducationDTO.class));
    }
}
