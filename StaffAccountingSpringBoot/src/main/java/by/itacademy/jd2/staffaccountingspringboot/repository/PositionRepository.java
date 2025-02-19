package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    Page<PositionEntity> findAllByDepartmentIdAndIsActual(Long departmentId,
                                                          Boolean isActual,
                                                          Pageable pageable);
    List<PositionEntity> findAllByIsActualTrue();
}
