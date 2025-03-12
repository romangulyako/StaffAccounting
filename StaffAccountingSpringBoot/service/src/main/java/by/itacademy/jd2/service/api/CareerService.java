package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.AppointmentInfoDTO;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.dto.EditCareerDTO;
import org.springframework.data.domain.Page;

public interface CareerService {
    /**
     * Назначает на должность сотрудника
     * @param careerStepDTO объект, содержащий данные для назначения
     */
    void appointEmployee(CareerStepSaveDTO careerStepDTO);

    /**
     * Увольняет сотрудника
     * @param dismissDTO объект, содержащий данные для увольнения
     */
    void dismissEmployee(DismissDTO dismissDTO);

    /**
     * Редактирует запись о карьере сотрудника
     * @param careerStepDTO отредактированная запись о карьере
     */
    void editCareerStep(CareerStepSaveDTO careerStepDTO);

    /**
     * Удаляет из БД запись о карьере сотрудника
     * @param id идентификатор записи о карьере
     */
    void deleteCareerStep(Long id);

    /**
     * Получает из БД информацию о конкретной записи карьеры сотрудника
     * и остальные данные для редактирования карьеры
     * @param id идентификатор записи о карьере
     * @return информация, необходимая для редактирования карьеры
     */
    EditCareerDTO getInfoForEditingCareerStep(Long id);

    /**
     * Получает страницу с записями о карьере сотрудника
     * @param employeeId идентификатор сотрудника
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с записями о карьере
     */
    Page<CareerStepGetDTO> getEmployeesCareer(Long employeeId, int page, int size);

    /**
     * Получает из БД информацию, необходимую для назначения сотрудников на должность
     * @return информация для назначения
     */
    AppointmentInfoDTO getAppointmentInfo();
}
