package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;

public interface RelativeService extends Service {
    void addRelative(RelativeDTO relativeDTO);
    void updateRelative(RelativeDTO relativeDTO);
    void deleteRelative(Serializable id);
    RelativeDTO getRelative(Serializable id);
    PageInfo<RelativeDTO> getRelativesByEmployeeIdAndPage(Serializable employeeId,
                                                          Integer pageNumber,
                                                          Integer pageSize);
}
