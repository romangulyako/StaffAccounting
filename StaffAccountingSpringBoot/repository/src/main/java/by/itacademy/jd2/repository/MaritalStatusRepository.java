package by.itacademy.jd2.repository;

import by.itacademy.jd2.entity.MaritalStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Long> {
    Page<MaritalStatusEntity> findAllByEmployeeIdOrderByRegistrationDate(Long employeeId, Pageable pageable);
}
