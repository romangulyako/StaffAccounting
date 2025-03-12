package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EditUserDTO;
import by.itacademy.jd2.dto.RoleDTO;
import by.itacademy.jd2.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministrationService {
    /**
     * Добавляет нового пользователя или обновляет существующего
     * @param userDTO объект с информацией о пользователе
     * @return объект DTO обновленного пользователя
     */
    UserDTO saveOrUpdateUser(UserDTO userDTO);

    /**
     * Удаляет пользователя по его ID
     * @param id идентификатор пользователя
     */
    void deleteUser(Long id);

    /**
     * Получает из БД пользователя по его ID
     * @param id идентификатор пользователя
     * @return объект EditUserDTO, хранящий информацию о пользователе и список всех ролей
     */
    EditUserDTO getUserById(Long id);

    /**
     * Получает страницу с пользователями
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с пользователями
     */
    Page<UserDTO> getAllUsers(int page, int size);

    /**
     * Получает список всех ролей из БД
     * @return список ролей
     */
    List<RoleDTO> getAllRoles();
}
