<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.MaritalStatusDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Семейное положение</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2>Информация о семейном положении сотрудника</h2>
        <% PageInfo<MaritalStatusDTO> pageInfo = (PageInfo<MaritalStatusDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<MaritalStatusDTO> maritalStatuses = pageInfo.getItems();
            if (maritalStatuses == null || maritalStatuses.isEmpty()) { %>
        <h3>Нет информации о семейном положении сотрудника</h3>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>Семейное положение</th>
                <th>Дата регистрации</th>
                <th>Подтверждающий документ</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
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
            </tbody>
        </table>
        <form action="<%=ConstantAction.MARITAL_STATUSES%>"
              method="get">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                   value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>"/>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
        <% } %>
        <div class="footer-buttons-block">
            <form name="add_marital_status"
                  method="get"
                  action="<%= ConstantAction.ADD_MARITAL_STATUS %>">
                <button class="footer-button, button-add"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID) %>">
                    Обновить семейное положение
                </button>
            </form>
            <form name="employee"
                  method="get"
                  action="<%= ConstantAction.EMPLOYEE %>">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                    Вернуться к просмотру сотрудника
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
