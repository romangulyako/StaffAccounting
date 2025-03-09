package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.EditUserDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.RoleDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministrationService {
    UserDTO saveOrUpdateUser(UserDTO userDTO);
    void deleteUser(Long id);
    EditUserDTO getUserById(Long id);
    Page<UserDTO> getAllUsers(int page, int size);
    List<RoleDTO> getAllRoles();
}
