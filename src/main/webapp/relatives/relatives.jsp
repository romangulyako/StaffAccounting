<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Родственники</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<div class="form-container">
    <% List<RelativeDTO> relatives = (List<RelativeDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_RELATIVES);
        if (relatives == null || relatives.isEmpty()) { %>
    <div class="item-header">
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
                <td><%= relative.getPersonData().getSurname() %>
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
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= relative.getId() %>">
                            Изменить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= relative.getEmployeeId() %>"/>
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
                               value="<%= relative.getEmployeeId() %>"/>
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
