package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.RelativeEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.RelativeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.EmployeeUtils;
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
public class RelativeServiceImpl implements RelativeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelativeServiceImpl.class);
    private static final String NOT_FOUND_EXCEPTION = "Relative not found. ID=";
    private final RelativeRepository relativeRepository;

    @Override
    public void saveOrUpdateRelative(RelativeDTO relativeDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_RELATIVE,
                relativeDTO.getPersonData().getName(), relativeDTO.getPersonData().getSurname());
        RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
        relativeRepository.save(relativeEntity);
        LOGGER.info(Constant.SAVE_RELATIVE_SUCCESS, relativeEntity.getId());
    }

    @Override
    public void deleteRelative(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_RELATIVE, id);
        relativeRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_RELATIVE_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public RelativeDTO getRelative(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_RELATIVE, id);
        RelativeEntity relativeEntity = relativeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(Constant.RELATIVE_NOT_FOUND, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });

        LOGGER.info(Constant.GET_RELATIVE_SUCCESS, id);
        return Converter.toDto(relativeEntity, RelativeDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RelativeDTO> getRelatives(Long employeeId, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_RELATIVE_LIST, employeeId);
        EmployeeUtils.findById(employeeId);
        Page<RelativeEntity> entities = relativeRepository.findAllByEmployeeId(employeeId, PageRequest.of(page, size));
        if (entities.getContent().isEmpty()) {
            LOGGER.warn(Constant.NOT_FOUND_RELATIVE_LIST, employeeId, page, size);
        } else {
            LOGGER.info(Constant.GET_RELATIVE_LIST_SUCCESS, entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, RelativeDTO.class));
    }
}
