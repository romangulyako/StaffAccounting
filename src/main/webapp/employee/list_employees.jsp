<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Сотрудники</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Все сотрудники организации:</h2>
    </div>
    <div class="form-group">
        <table>
            <tr>
                <th>ФИО</th>
                <th>Дата рождения</th>
                <th>Должность</th>
                <th colspan="2">Действие</th>
            </tr>
            <% PageInfo<EmployeeDTO> pageItems = (PageInfo<EmployeeDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE);
                List<EmployeeDTO> employees = pageItems.getItems();
                for (EmployeeDTO employee : employees) {
            %>
            <tr>
                <td>
                    <%= employee.getPersonData().getSurname() + " "
                            + employee.getPersonData().getName()%>
                    <%if (employee.getPersonData().getPatronymic() != null) { %>
                    <%=" " + employee.getPersonData().getPatronymic()%>
                    <% } %>
                </td>
                <td><%= employee.getPersonData().getBirthday() %>
                </td>
                <td>
                    <%if (employee.getPositionName() != null) { %>
                    <%=employee.getPositionName()%>
                    <% } %>
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
        <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
              method="get">
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageItems.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageItems.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageItems.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
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
