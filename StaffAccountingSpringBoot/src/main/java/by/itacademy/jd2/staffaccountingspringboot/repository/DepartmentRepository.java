package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Page<DepartmentEntity> findAllByIsActual(Pageable pageable, Boolean isActual);
}
