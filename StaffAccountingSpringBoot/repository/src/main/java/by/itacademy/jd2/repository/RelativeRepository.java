package by.itacademy.jd2.repository;

import by.itacademy.jd2.entity.RelativeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeRepository extends JpaRepository<RelativeEntity, Long> {
    Page<RelativeEntity> findAllByEmployeeId(Long employeeId, Pageable pageable);
}
