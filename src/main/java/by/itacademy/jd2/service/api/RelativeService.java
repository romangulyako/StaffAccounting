package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.RelativeDTO;

import java.io.Serializable;
import java.util.List;

public interface RelativeService extends Service {
    void addRelative(RelativeDTO relativeDTO);
    void updateRelative(RelativeDTO relativeDTO);
    void deleteRelative(Serializable id);
    RelativeDTO getRelative(Serializable id);
    List<RelativeDTO> getRelatives(Long employeeId);
}
