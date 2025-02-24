package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EducationEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EducationRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Education saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Education with ID={} deleted successfully";
    private static final String NOT_FOUND_LOG = "Education with ID={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Education not found. ID=";
    private static final String FOUND_SUCCESS_LOG = "Successfully fetched education with ID={} from database";
    private static final String NOT_FOUND_LIST_LOG =
            "No education found for employee ID={} for the provided parameters: page={}, size={}";
    private static final String FOUND_LIST_SUCCESS_LOG = "Successfully fetched {} education rows from the database";
    private final EducationRepository educationRepository;

    @Override
    public void saveOrUpdateEducation(EducationDTO educationDTO) {
        EducationEntity entity = Converter.toEntity(educationDTO, EducationEntity.class);
        educationRepository.save(entity);
        LOGGER.info(SAVE_SUCCESS_LOG, entity.getId());
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public EducationDTO getEducation(Long id) {
        EducationEntity educationEntity = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });

        LOGGER.info(FOUND_SUCCESS_LOG, id);
        return Converter.toDto(educationEntity, EducationDTO.class);
    }

    @Override
    public Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, int page, int size) {
        Page<EducationEntity> entities =
                educationRepository.findAllByEmployeeIdOrderByDateEndAsc(employeeId, PageRequest.of(page, size));
        if (entities.getContent().isEmpty()) {
            LOGGER.warn(NOT_FOUND_LIST_LOG, employeeId, page, size);
        } else {
            LOGGER.info(FOUND_LIST_SUCCESS_LOG, entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, EducationDTO.class));
    }
}
