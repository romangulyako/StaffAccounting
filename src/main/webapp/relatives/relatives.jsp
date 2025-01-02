<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Родственники</title>
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

        .relative-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .relative-header h2 {
            margin: 0;
        }

        .button-add {
            background-color: #259925; /* Зеленый цвет */
            color: #ffffff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-add:hover {
            background-color: #218721; /* Более темный зеленый цвет при наведении */
        }

        .tabs {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .tab {
            margin: 0 10px;
            padding: 10px 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
            cursor: pointer;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease;
        }

        .tab:hover {
            background-color: #e9e9e9;
        }
    </style>
</head>
<body>
<div class="form-container">
    <% List<RelativeDTO> relatives = (List<RelativeDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_RELATIVES);
        if (relatives == null || relatives.isEmpty()) { %>
    <div class="relative-header">
        <h2>Информация о родственниках отсутствует</h2>
    </div>
    <% } else { %>
    <div class="form-group">
        <table>
            <tr>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Дата рождения</th>
                <th>Тип родства</th>
                <th colspan="2">Действие</th>
            </tr>
            <tr>
                <% for (RelativeDTO relative : relatives) { %>
                <%--@declare id="delete_relative"--%><%--@declare id="delete_relative, update_relative"--%><td><%= relative.getPersonData().getSurname() %>
                </td>
                <td><%= relative.getPersonData().getName() %>
                </td>
                <td><%= relative.getPersonData().getPatronymic() %>
                </td>
                <td><%= relative.getPersonData().getBirthday() %>
                </td>
                <td><%= relative.getTypeKinship() %>
                </td>
                <td>
                    <form name="update_relative"
                          method="get"
                          action="<%= ConstantAction.UPDATE_RELATIVE %>">
                        <button class="button-show"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= relative.getId() %>">
                            Изменить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_relative"
                          method="post"
                          action="<%= ConstantAction.DELETE_RELATIVE %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= relative.getId() %>">
                            Удалить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
    <% } %>
    <div class="tabs">
        <form name="add_relative"
              method="get"
              action="<%= ConstantAction.ADD_RELATIVE %>">
            <button class="tab, button-add"
                    name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Добавить родственника
            </button>
        </form>
        <form name="employee"
              method="get"
              action="<%= ConstantAction.EMPLOYEE %>">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру сотрудника
            </button>
        </form>
    </div>
</div>
</body>
</html>
