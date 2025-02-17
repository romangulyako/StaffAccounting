package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.RelativeEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.RelativeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
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
public class RelativeServiceImpl implements RelativeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelativeServiceImpl.class);
    private final RelativeRepository relativeRepository;

    @Override
    public void saveOrUpdateRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
        relativeRepository.save(relativeEntity);
        LOGGER.info("Relative saved successfully. ID={}", relativeEntity.getId());
    }

    @Override
    public void deleteRelative(Long id) {
        relativeRepository.deleteById(id);
        LOGGER.info("Relative with id={} deleted successfully", id);
    }

    @Override
    public RelativeDTO getRelative(Long id) {
        RelativeEntity relativeEntity = relativeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info("Relative with id={} not found", id);
                    return new EntityNotFoundException("Relative with id " + id + " not found");
                });

        LOGGER.info("Successfully fetched relative with id={} from database", id);
        return Converter.toDto(relativeEntity, RelativeDTO.class);
    }

    @Override
    public Page<RelativeDTO> getRelatives(Long employeeId, Pageable pageable) {
        Page<RelativeEntity> entities = relativeRepository.findAllByEmployeeId(employeeId, pageable);
        if (entities.getContent().isEmpty()) {
            LOGGER.warn("No relatives found for employee ID={} for the provided parameters: page={}, size={}",
                    employeeId, pageable.getPageNumber(), pageable.getPageSize());
        } else {
            LOGGER.info("Successfully fetched {} relatives from the database", entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, RelativeDTO.class));
    }
}
