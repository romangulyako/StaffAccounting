<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Сотрудники</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-container {
            background-color: #f4f4f4;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 90%;
        }

        .form-group {
            margin-bottom: 15px;
        }

        button {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-add {
            background-color: #259925;
            color: #ffffff;
        }

        .button-add:hover {
            background-color: #218721;
        }

        .button-delete {
            background-color: #e41c1c;
            color: #ffffff;
        }

        .button-delete:hover {
            background-color: #cd1313;
        }

        .button-show {
            background-color: #3258ea;
            color: #ffffff;
        }

        .button-show:hover {
            background-color: #2246cf;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
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
