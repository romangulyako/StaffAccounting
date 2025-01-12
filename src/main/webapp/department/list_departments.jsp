<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отделы</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Структура организации:</h2>
    </div>
    <div class="form-group">
        <table>
            <tr>
                <th>Наименование</th>
                <th>Описание отдела</th>
                <th>Количество должностей</th>
                <th colspan="2">Действие</th>
            </tr>
            <% List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_DEPARTMENTS);
                for (DepartmentDTO department : departments) { %>
            <tr>
                <td><%=department.getName()%>
                </td>
                <td>
                    <%if (department.getDescription() != null) { %>
                    <%=department.getDescription()%>
                    <% } %>
                </td>
                <td><%=department.getPositionsCount()%></td>
                <td>
                    <form name="employee"
                          method="get"
                          action="<%= ConstantAction.DEPARTMENT_INFO %>">
                        <button class="button-show"
                                name="<%= ConstantParamAndAttribute.DEPARTMENT_ID %>"
                                value="<%= department.getId() %>">
                            Просмотреть
                        </button>
                    </form>
                </td>
                <td>
                    <form name="delete_employee"
                          method="post"
                          action="<%= ConstantAction.DELETE_DEPARTMENT %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= department.getId() %>">
                            Удалить
                        </button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
              method="get">
            <%@include file="../paginator.jsp"%>
        </form>
    </div>
    <div>
        <form name="add_department"
              method="get"
              action="<%= ConstantAction.ADD_DEPARTMENT %>">
            <button class="button-add">Добавить новый отдел</button>
        </form>
    </div>
</div>
</body>
</html>
