<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.MaritalStatusDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Семейное положение</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="item-header">
        <h2>Информация о семейном положении сотрудника</h2>
    </div>
    <% List<MaritalStatusDTO> maritalStatuses = (List<MaritalStatusDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_MARITAL_STATUSES);
        if (maritalStatuses == null || maritalStatuses.isEmpty()) { %>
    <div class="form-group">
        <h3>Нет информации о семейном положении сотрудника</h3>
    </div>
    <% } else { %>
    <div class="form-group">
        <table>
            <tr>
                <th>Семейное положение</th>
                <th>Дата регистрации</th>
                <th>Подтверждающий документ</th>
                <th colspan="2">Действие</th>
            </tr>
            <tr>
                <% for (MaritalStatusDTO maritalStatus : maritalStatuses) { %>
                <td><%= maritalStatus.getStatus() %>
                </td>
                <td><%= maritalStatus.getRegistrationDate() %>
                </td>
                <td><%= maritalStatus.getDocument() %>
                </td>
                <td>
                    <form name="update_marital_status"
                          method="get"
                          action="<%= ConstantAction.UPDATE_MARITAL_STATUS %>">
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= maritalStatus.getId() %>">
                            Изменить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= maritalStatus.getEmployeeId() %>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_marital_status"
                          method="post"
                          action="<%= ConstantAction.DELETE_MARITAL_STATUS %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= maritalStatus.getId() %>">
                            Удалить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= maritalStatus.getEmployeeId() %>"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
    <% } %>
    <div class="tabs">
        <form name="add_marital_status"
              method="get"
              action="<%= ConstantAction.ADD_MARITAL_STATUS %>">
            <button class="tab, button-add"
                    name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID) %>">
                Обновить семейное положение
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
