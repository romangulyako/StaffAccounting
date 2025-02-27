package by.itacademy.jd2.staffaccountingspringboot.repository;

import by.itacademy.jd2.staffaccountingspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
