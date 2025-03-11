package by.itacademy.jd2.staffaccountingspringboot.utils;

public class Constant {
    /*** Log messages ***/
    public static final String ATTEMPT_TO_SAVE_EMPLOYEE = "Attempt to save an employee with name={}, surname={}";
    public static final String SAVE_EMPLOYEE_SUCCESS = "Employee saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_EMPLOYEE = "Attempt to delete an employee with ID={}";
    public static final String DELETE_EMPLOYEE_SUCCESS = "Employee with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_EMPLOYEE = "Attempt to fetch employee with ID={}";
    public static final String FETCHED_EMPLOYEE_SUCCESS = "Successfully fetched employee with ID={} from database";
    public static final String ATTEMPT_TO_FETCH_EMPLOYEE_LIST = "Attempt to fetch employee's list";
    public static final String NOT_FOUND_EMPLOYEE_LIST =
            "No employees found for the provided parameters: page={}, size={}";
    public static final String FOUND_EMPLOYEE_LIST_SUCCESS = "Successfully fetched {} employees from the database";
    public static final String ATTEMPT_TO_RETURN_EMPLOYEE = "Attempt to return employee with ID={} to current";
    public static final String RETURN_EMPLOYEE_TO_CURRENT_SUCCESS =
            "Employee with ID={} returned to current successfully";
    public static final String EMPLOYEE_NOT_FOUND = "Employee with ID={} not found";
    public static final String ATTEMPT_TO_APPOINT_EMPLOYEE = "Attempt to appoint employee ID={} to position ID={}";
    public static final String APPOINT_EMPLOYEE_SUCCESS =
            "Employee with ID={} appointed to position with ID={} successfully";
    public static final String ATTEMPT_TO_DISMISS_EMPLOYEE = "Attempt to dismiss employee with ID={}";
    public static final String DISMISS_EMPLOYEE_SUCCESS = "Employee with ID={} dismissed successfully";
    public static final String ATTEMPT_TO_EDIT_CAREER_STEP = "Attempt to edit career step with ID={}";
    public static final String EDIT_CAREER_STEP_SUCCESS = "Career step with ID={} edited successfully";
    public static final String ID_IS_NULL_LOG = "Career can't be to edit, ID is null";
    public static final String ATTEMPT_TO_DELETE_CAREER_STEP = "Attempt to delete career step with ID={}";
    public static final String DELETE_CAREER_STEP_SUCCESS = "Career step with ID={} deleted successfully";
    public static final String ATTEMPT_TO_GET_INFO_FOR_EDITING_CAREER_STEP =
            "Attempt to get info for editing career step with ID={}";
    public static final String INFO_FOR_EDITING_CAREER_STEP_FETCHED_SUCCESS =
            "Info for editing career step with ID={} fetched success";
    public static final String CAREER_STEP_NOT_FOUND = "Career step with ID={} not found";
    public static final String NO_CAREER_STEPS_FOUND =
            "No career steps found for the provided parameters: page={}, size={}";
    public static final String ATTEMPT_TO_GET_CAREER_OF_EMPLOYEE = "Attempt to get career of employee with ID={}";
    public static final String SUCCESS_CAREER_FOUND = "Successfully fetched {} career steps from database";
    public static final String ATTEMPT_TO_GET_INFO_FOR_APPOINTMENT = "Attempt to get info for appointment employees";
    public static final String EMPLOYEE_AND_POSITION_ITEMS_FOUND_LOG =
            "Info for appointment employees fetched successfully";
    public static final String ATTEMPT_TO_SAVE_DEPARTMENT = "Attempt to save department with name={}";
    public static final String SAVE_DEPARTMENT_SUCCESS = "Department saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_DEPARTMENT = "Attempt to delete department with ID={}";
    public static final String DELETE_DEPARTMENT_SUCCESS = "Department with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_DEPARTMENT = "Attempt to fetch department with ID={}";
    public static final String DEPARTMENT_FOUND_SUCCESS = "Successfully fetched department with ID={} from database";
    public static final String ATTEMPT_TO_FETCH_INFO_OF_DEPARTMENT = "Attempt to fetch info of department with ID={}";
    public static final String FETCHED_DEPARTMENT_INFO_SUCCESS = "Successfully fetched department's info with ID={}";
    public static final String ATTEMPT_TO_FETCH_DEPARTMENTS_LIST = "Attempt to fetch department's list";
    public static final String NO_DEPARTMENTS_FOUND =
            "No departments found for the provided parameters: page={}, size={}";
    public static final String DEPARTMENTS_LIST_FOUND_SUCCESS = "Successfully fetched {} departments from database";
    public static final String ATTEMPT_TO_REDUCE_DEPARTMENT = "Attempt to reduce department with ID={}";
    public static final String REDUCE_DEPARTMENT_SUCCESS = "Successfully reduced department with ID={}";
    public static final String ATTEMPT_TO_RESTORE_DEPARTMENT = "Attempt to restore department with ID={}";
    public static final String RESTORE_DEPARTMENT_SUCCESS = "Successfully restored department with ID={}";
    public static final String DEPARTMENT_NOT_FOUND = "Department with ID={} not found";
    public static final String ATTEMPT_TO_SAVE_EDUCATION = "Attempt to save education";
    public static final String SAVE_EDUCATION_SUCCESS = "Education saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_EDUCATION = "Attempt to delete education with ID={}";
    public static final String DELETE_SUCCESS_LOG = "Education with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_EDUCATION = "Attempt to fetch for editing education with ID={}";
    public static final String EDUCATION_NOT_FOUND = "Education with ID={} not found";
    public static final String EDUCATION_FOUND_SUCCESS = "Successfully fetched education with ID={} from database";
    public static final String ATTEMPT_TO_GET_EDUCATION_FOR_EMPLOYEE =
            "Attempt to get education for employee with ID={}";
    public static final String NOT_FOUND_EDUCATION_LIST =
            "No education found for employee ID={} for the provided parameters: page={}, size={}";
    public static final String FOUND_EDUCATION_LIST_SUCCESS =
            "Successfully fetched {} education rows from the database";
    public static final String ATTEMPT_TO_SAVE_MARITAL_STATUS =
            "Attempt to save marital status for employee with ID={}";
    public static final String SAVE_MARITAL_STATUS_SUCCESS = "Marital status saved successfully. ID: {}";
    public static final String ATTEMPT_TO_DELETE_MARITAL_STATUS = "Attempt to delete marital status with ID={}";
    public static final String DELETE_MARITAL_STATUS_SUCCESS = "Marital status with ID={} deleted successfully";
    public static final String ATTEMPT_TO_GET_MARITAL_STATUS = "Attempt to get marital status with ID={}";
    public static final String NOT_FOUND_MARITAL_STATUS = "Marital status with ID={} not found";
    public static final String FOUND_MARITAL_STATUS_SUCCESS =
            "Successfully fetched marital status with ID={} from database";
    public static final String ATTEMPT_TO_GET_MARITAL_STATUSES_BY_EMPLOYEE =
            "Attempt to get marital statuses by employee with ID={}";
    public static final String NOT_FOUND_MARITAL_STATUS_LIST =
            "No marital statuses found for employee ID={} for the provided parameters: page={}, size={}";
    public static final String FOUND_MARITAL_STATUS_LIST_SUCCESS =
            "Successfully fetched {} marital statuses from the database";
    public static final String ATTEMPT_TO_SAVE_POSITION = "Attempt to save position for department with ID={}";
    public static final String SAVE_POSITION_SUCCESS = "Position saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_POSITION = "Attempt to delete position with ID={}";
    public static final String DELETE_POSITION_SUCCESS = "Position with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_POSITION = "Attempt to fetch position with ID={}";
    public static final String GET_POSITION_SUCCESS = "Successfully fetched position with ID={}";
    public static final String ATTEMPT_TO_REDUCE_POSITION = "Attempt to reduce position with ID-{}";
    public static final String REDUCE_POSITION_SUCCESS = "Successfully reduced position with ID={}";
    public static final String ATTEMPT_TO_RESTORE_POSITION = "Attempt to restore position with ID-{}";
    public static final String RESTORE_POSITION_SUCCESS = "Successfully restored position with ID={}";
    public static final String ATTEMPT_TO_GET_HISTORY_OF_POSITION = "Attempt to get history of position with ID={}";
    public static final String NOT_FOUND_POSITION_HISTORY =
            "No rows found for the provided parameters: page={}, size={}";
    public static final String GET_POSITION_HISTORY_SUCCESS = "Successfully fetched {} rows of history from database";
    public static final String POSITION_NOT_FOUND = "Position with ID={} not found";
    public static final String ATTEMPT_TO_SAVE_PASSPORT = "Attempt to save passport by employee with ID={}";
    public static final String SAVE_SUCCESS_LOG = "Passport saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_PASSPORT = "Attempt to delete passport by employee with ID={}";
    public static final String DELETE_PASSPORT_SUCCESS_LOG = "Passport of employee with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_PASSPORT = "Attempt to fetch passport by employee with ID={}";
    public static final String PASSPORT_NOT_FOUND = "Passport of employee with ID={} not found";
    public static final String PASSPORT_FOUND_SUCCESS = "Successfully fetched passport with ID={} from database";
    public static final String ATTEMPT_TO_SAVE_RELATIVE = "Attempt to save relative with name={}, surname={}";
    public static final String SAVE_RELATIVE_SUCCESS = "Relative saved successfully. ID={}";
    public static final String ATTEMPT_TO_DELETE_RELATIVE = "Attempt to delete relative with ID={}";
    public static final String DELETE_RELATIVE_SUCCESS = "Relative with ID={} deleted successfully";
    public static final String ATTEMPT_TO_FETCH_RELATIVE = "Attempt to fetch relative with ID={}";
    public static final String RELATIVE_NOT_FOUND = "Relative with ID={} not found";
    public static final String GET_RELATIVE_SUCCESS = "Successfully fetched relative with ID={} from database";
    public static final String ATTEMPT_TO_GET_RELATIVE_LIST = "Attempt to get relatives by employee with ID={}";
    public static final String NOT_FOUND_RELATIVE_LIST =
            "No relatives found for employee ID={} for the provided parameters: page={}, size={}";
    public static final String GET_RELATIVE_LIST_SUCCESS = "Successfully fetched {} relatives from the database";
    public static final String ATTEMPT_TO_SAVE_USER = "Attempt to save user with username = {}";
    public static final String SUCCESSFULLY_SAVED_USER = "Successfully saved user with ID = {}";
    public static final String ATTEMPT_TO_DELETE_USER = "Attempt to delete user with ID = {}";
    public static final String SUCCESSFULLY_DELETED_USER = "Successfully deleted user with ID = {}";
    public static final String ATTEMPT_TO_GET_USER = "Attempt to get user with ID = {}";
    public static final String SUCCESSFULLY_FETCHED_USER = "Successfully fetched user with ID = {}";
    public static final String ATTEMPT_TO_GET_PAGE_WITH_USERS = "Attempt to get page with users";
    public static final String SUCCESSFULLY_FETCHED_PAGE_WITH_USERS =
            "Successfully fetched page with users. page={}, size={}";
    public static final String NO_FOUND_USERS = "No found users for page's parameters: page={}, size={}";

    /*** Exception's message keys ***/
    public static final String APPOINTED_EXCEPTION_UNIQUE_MESSAGE_KEY = "employee_already_appointed";
    public static final String CAREER_STEP_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "career_step_not_found";
    public static final String DEPARTMENT_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "department_not_found";
    public static final String EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "education_not_found";
    public static final String EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "employee_not_found";
    public static final String MARITAL_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "marital_not_found";
    public static final String POSITION_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "position_not_found";
    public static final String RELATIVE_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "relative_not_found";
    public static final String URL_PARAMETER_EXCEPTION_MESSAGE_KEY = "url_parameter_wrong";
    public static final String ACCESS_DENIED_EXCEPTION_MESSAGE_KEY = "access_denied";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "user_name_not_found";
    public static final String USER_BY_ID_NOT_FOUND_EXCEPTION_MESSAGE_KEY = "user_by_id_not_found";

    private Constant() {}
}