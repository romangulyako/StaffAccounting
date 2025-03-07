package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByAuthority(String authorityName);
}
