package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.repository.EducationRepository;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.utils.Constant;
import by.itacademy.jd2.utils.EmployeeUtils;
import by.itacademy.jd2.utils.LocaleUtils;
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
    private final EducationRepository educationRepository;

    @Override
    public EducationDTO saveOrUpdateEducation(EducationDTO educationDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_EDUCATION);
        EducationEntity entity = educationRepository.save(Converter.toEntity(educationDTO, EducationEntity.class));
        LOGGER.info(Constant.SAVE_EDUCATION_SUCCESS, entity.getId());

        return Converter.toDto(entity, EducationDTO.class);
    }

    @Override
    public void deleteEducation(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_EDUCATION, id);
        educationRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_SUCCESS_LOG, id);
    }

    @Transactional(readOnly = true)
    @Override
    public EducationDTO getEducation(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_EDUCATION, id);
        EducationEntity educationEntity = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(Constant.EDUCATION_NOT_FOUND, id);
                    return new EntityNotFoundException(LocaleUtils
                            .getMessage(Constant.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id);
                });

        LOGGER.info(Constant.EDUCATION_FOUND_SUCCESS, id);
        return Converter.toDto(educationEntity, EducationDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_EDUCATION_FOR_EMPLOYEE, employeeId);
        EmployeeUtils.checkExistEmployee(employeeId);
        Page<EducationEntity> entities =
                educationRepository.findAllByEmployeeIdOrderByDateEndAsc(employeeId, PageRequest.of(page, size));
        if (entities.getContent().isEmpty()) {
            LOGGER.warn(Constant.NOT_FOUND_EDUCATION_LIST, employeeId, page, size);
        } else {
            LOGGER.info(Constant.FOUND_EDUCATION_LIST_SUCCESS, entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, EducationDTO.class));
    }
}
