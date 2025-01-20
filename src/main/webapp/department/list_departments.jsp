<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Отделы</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <% PageInfo<DepartmentDTO> pageInfo = (PageInfo<DepartmentDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<DepartmentDTO> departments = pageInfo.getItems();
            Boolean isActual = (Boolean) request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL);%>

        <h2>Структура организации</h2>
        <table>
            <thead>
            <tr>
                <th>Наименование</th>
                <th>Описание отдела</th>
                <th>Количество должностей</th>
                <th colspan="3">Действие</th>
            </tr>
            </thead>
            <tbody>
            <% for (DepartmentDTO department : departments) { %>
            <tr>
                <td><%=department.getName()%>
                </td>
                <td>
                    <%if (department.getDescription() != null) { %>
                    <%=department.getDescription()%>
                    <% } %>
                </td>
                <td>
                    <%if (isActual) { %>
                    <%=department.getActualPositionsCount()%>
                    <% } else { %>
                    <%=department.getReducedPositionsCount()%>
                    <% } %>
                </td>
                <td>
                    <form name="department_info"
                          method="get"
                          action="<%=ConstantAction.DEPARTMENT_INFO%>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <button class="button-show"
                                name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                                value="<%=isActual%>">
                            Просмотр
                        </button>
                    </form>
                </td>
                <td>
                    <form name="delete_department"
                          method="post"
                          action="<%=ConstantAction.DELETE_DEPARTMENT%>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.ID%>"
                               value="<%=department.getId()%>"/>
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.IS_ACTUAL %>"
                                value="<%= isActual%>">
                            Удалить
                        </button>
                    </form>
                </td>
                <td>
                    <%if (isActual) { %>
                    <form name="reduce_employee"
                          method="post"
                          action="<%= ConstantAction.REDUCE_DEPARTMENT %>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <button class="button-reduce"
                                name="<%= ConstantParamAndAttribute.IS_ACTUAL %>"
                                value="<%= true %>">
                            Сократить
                        </button>
                    </form>
                    <% } else { %>
                    <form name="reduce_employee"
                          method="post"
                          action="<%= ConstantAction.RESTORE_DEPARTMENT %>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <button class="button-restore"
                                name="<%= ConstantParamAndAttribute.IS_ACTUAL %>"
                                value="<%= false %>">
                            Восстановить
                        </button>
                    </form>
                    <% } %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
              method="get">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%= isActual %>"/>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
        <% if (isActual) { %>
        <form name="add_department"
              method="get"
              action="<%= ConstantAction.ADD_DEPARTMENT %>">
            <button class="button-add">Добавить новый отдел</button>
        </form>
        <% } %>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
