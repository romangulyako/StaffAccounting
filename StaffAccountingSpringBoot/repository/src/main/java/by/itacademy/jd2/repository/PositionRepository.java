package by.itacademy.jd2.repository;

import by.itacademy.jd2.entity.PositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    Page<PositionEntity> findAllByDepartmentIdAndIsActual(Long departmentId,
                                                          Boolean isActual,
                                                          Pageable pageable);
    List<PositionEntity> findAllByIsActualTrue();
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM PositionEntity p JOIN p.history c " +
            "WHERE p.id = :positionId AND c.isCurrent = true")
    boolean existsCurrentCareerInHistory(@Param("positionId") Long positionId);
}
