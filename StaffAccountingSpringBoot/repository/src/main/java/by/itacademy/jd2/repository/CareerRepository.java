package by.itacademy.jd2.repository;

import by.itacademy.jd2.entity.CareerStepEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<CareerStepEntity, Long> {
    Page<CareerStepEntity> findAllByPositionIdOrderByDateOfAppointment(Long positionId, Pageable pageable);
    Page<CareerStepEntity> findAllByEmployeeIdOrderByDateOfAppointment(Long employeeId, Pageable pageable);
    List<CareerStepEntity> findAllByEmployeeIdAndIsCurrentTrue(Long employeeId);
}
