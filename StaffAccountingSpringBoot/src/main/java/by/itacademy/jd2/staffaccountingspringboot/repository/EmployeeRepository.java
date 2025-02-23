package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.repository.custom.EmployeeFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, EmployeeFilterRepository {
    List<EmployeeEntity> findAllByIsFiredFalse();
    @Modifying
    @Query("UPDATE EmployeeEntity e SET e.isFired = true WHERE e.id = :id")
    void updateIsFired(@Param("id") Long id);
}

