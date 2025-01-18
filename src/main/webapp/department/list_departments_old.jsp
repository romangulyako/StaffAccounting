<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                <th colspan="3">Действие</th>
            </tr>
            <% PageInfo<DepartmentDTO> pageInfo = (PageInfo<DepartmentDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
                List<DepartmentDTO> departments = pageInfo.getItems();
                Boolean isActual = (Boolean) request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL);
                for (DepartmentDTO department : departments) { %>
            <tr>
                <td><%=department.getName()%>
                </td>
                <td>
                    <%if (department.getDescription() != null) { %>
                    <%=department.getDescription()%>
                    <% } %>
                </td>
                <td>
                    <%if (isActual == null || isActual) { %>
                    <%=department.getActualPositionsCount()%>
                    <% } else { %>
                    <%=department.getReducedPositionsCount()%>
                    <% } %>
                </td>
                <td>
                    <form name="department"
                          method="get"
                          action="<%= ConstantAction.DEPARTMENT_INFO %>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%=isActual%>"/>
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
                <td>
                    <%if (isActual == null || isActual) { %>
                    <form name="reduce_employee"
                          method="post"
                          action="<%= ConstantAction.REDUCE_DEPARTMENT %>">
                        <button class="button-reduce"
                                name="<%= ConstantParamAndAttribute.DEPARTMENT_ID %>"
                                value="<%= department.getId() %>">
                            Сократить
                        </button>
                    </form>
                    <% } else { %>
                    <form name="reduce_employee"
                          method="post"
                          action="<%= ConstantAction.RESTORE_DEPARTMENT %>">
                        <button class="button-restore"
                                name="<%= ConstantParamAndAttribute.DEPARTMENT_ID %>"
                                value="<%= department.getId() %>">
                            Восстановить
                        </button>
                    </form>
                    <% } %>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
              method="get">
            <%if (Boolean.FALSE.equals(isActual)) { %>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%=isActual%>"/>
            <% } %>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
    </div>
    <%
        if (isActual == null || isActual) {
    %>
    <div>
        <form name="add_department"
              method="get"
              action="<%= ConstantAction.ADD_DEPARTMENT %>">
            <button class="button-add">Добавить новый отдел</button>
        </form>
    </div>
    <% } %>
</div>
</body>
</html>
