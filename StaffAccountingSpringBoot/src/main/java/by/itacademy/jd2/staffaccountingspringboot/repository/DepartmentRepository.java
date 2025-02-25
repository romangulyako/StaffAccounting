package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    @Query("SELECT DISTINCT d FROM DepartmentEntity d " +
            "LEFT JOIN d.positions p ON d.id = p.department.id " +
            "WHERE d.isActual = :isActual OR  p.isActual = :isActual")
    Page<DepartmentEntity> findAllByIsActual(Pageable pageable, @Param("isActual") Boolean isActual);
    List<DepartmentEntity> findAllByIsActualTrue();
}
