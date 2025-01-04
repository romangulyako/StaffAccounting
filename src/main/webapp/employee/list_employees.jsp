<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Сотрудники</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<div class="form-container">
    <div class="form-group">
        <h2>Все сотрудники организации:</h2>
    </div>
    <div class="form-group">
        <table>
            <tr>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Дата рождения</th>
                <th>Домашний адрес</th>
                <th>Номер телефона</th>
                <th colspan="2">Действие</th>
            </tr>
            <% List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_EMPLOYEES);
                for (EmployeeDTO employee : employees) {
            %>
            <tr>
                <td><%= employee.getPersonData().getSurname() %>
                </td>
                <td><%= employee.getPersonData().getName() %>
                </td>
                <td><%= employee.getPersonData().getPatronymic() %>
                </td>
                <td><%= employee.getPersonData().getBirthday() %>
                </td>
                <td>
                    <%= employee.getHomeAddress().getCity() %>,
                    <%= employee.getHomeAddress().getStreet() %>,
                    <%= employee.getHomeAddress().getHouse() %>,
                    <%= employee.getHomeAddress().getApartment() %>
                </td>
                <td><%= employee.getPhone() %>
                </td>
                <td>
                    <form name="employee"
                          method="get"
                          action="<%= ConstantAction.EMPLOYEE %>">
                        <button class="button-show"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= employee.getId() %>">
                            Просмотреть
                        </button>
                    </form>
                </td>
                <td>
                    <form name="delete_employee"
                          method="post"
                          action="<%= ConstantAction.DELETE_EMPLOYEE %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= employee.getId() %>">
                            Удалить
                        </button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div>
        <form name="add_employee"
              method="get"
              action="<%= ConstantAction.ADD_EMPLOYEE %>">
            <button class="button-add">Добавить нового сотрудника</button>
        </form>
    </div>
</div>
</body>
</html>
