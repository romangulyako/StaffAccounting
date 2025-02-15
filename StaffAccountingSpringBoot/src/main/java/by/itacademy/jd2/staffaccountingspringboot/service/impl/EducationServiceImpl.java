package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EducationEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EducationRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
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
        LOGGER.info("Saved education with id={}", entity.getId());

    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);

        LOGGER.info("Deleted education with id={}", id);
    }

    @Override
    public EducationDTO getEducation(Long id) {
        EducationDTO educationDTO = Converter.toDto(educationRepository.findById(id).orElse(null), EducationDTO.class);
        LOGGER.info("Found education with id={}", id);
        return  educationDTO;
    }

    @Override
    public Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, Pageable pageable) {
        Page<EducationEntity> entities = educationRepository.findAllByEmployeeIdOrderByDateEndAsc(employeeId, pageable);
        LOGGER.info("Found {} from {} educations for employee with id={}",
                entities.stream().count(), entities.getTotalElements(), employeeId);

        return entities.map(entity -> Converter.toDto(entity, EducationDTO.class));
    }
}
