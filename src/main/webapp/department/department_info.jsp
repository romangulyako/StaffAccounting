<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<% Boolean isActual = (Boolean) request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL);
    DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT);
    PageInfo<PositionDTO> pageInfo = (PageInfo<PositionDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
    List<PositionDTO> positions = pageInfo.getItems();
%>
<head>
    <title><%=department.getName()%>
    </title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <div class="header-with-button">
            <h2>Информация об отделе</h2>
            <%if (isActual) { %>
            <form name="update_department"
                  action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
                  method="get">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=isActual%>"/>
                <button class="button-edit"
                        name="<%= ConstantParamAndAttribute.ID %>"
                        value="<%= department.getId() %>">
                    Изменить
                </button>
            </form>
            <% } %>
        </div>
        <div class="information-block">
            <div class="information-item">
                <label>Наименование:</label>
                <span><%=department.getName()%></span>
            </div>
            <div class="information-item">
                <label>Наименование в родительном падеже:</label>
                <span><%=department.getGenitiveCaseName()%></span>
            </div>
            <div class="information-item">
                <label>Описание:</label>
                <span>
                <%if (department.getDescription() != null) { %>
                <%=department.getDescription()%>
                <% } %>
                </span>
            </div>
        </div>
        <h2>Должности отдела</h2>
        <%if (positions == null || positions.isEmpty()) { %>
        <div class="form-group">
            <h3>Должностей нет</h3>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>Наименование должности</th>
                <th>Необходимый уровень образования</th>
                <th>Оклад по должности</th>
                <th colspan="4">Действие</th>
            </tr>
            </thead>
            <tbody>
            <% for (PositionDTO position : positions) { %>
            <tr>
                <td><%=position.getName()%>
                </td>
                <td><%=position.getEducationLevel()%>
                </td>
                <td><%=position.getSalary()%>
                </td>
                <td>
                    <form name="update_position"
                          method="get"
                          action="<%=ConstantAction.UPDATE_POSITION%>">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%= isActual %>"/>
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= position.getId() %>">
                            Изменить
                        </button>
                    </form>
                </td>
                <td>
                    <form name="delete_position"
                          action="<%=ConstantAction.DELETE_POSITION%>"
                          method="post">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%= department.getId() %>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%= isActual %>"/>
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= position.getId() %>">
                            Удалить
                        </button>
                    </form>
                </td>
                <td>
                    <form name="show_history"
                          action="<%=ConstantAction.POSITION_HISTORY%>"
                          method="get">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%= isActual %>"/>
                        <button class="button-show"
                                name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                value="<%= position.getId() %>">
                            Посмотреть историю
                        </button>
                    </form>
                </td>
                <td>
                    <%if (isActual) { %>
                    <form name="reduce_position"
                          action="<%=ConstantAction.REDUCE_POSITION%>"
                          method="post">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%= true %>"/>
                        <button class="button-reduce"
                                name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                value="<%= position.getId() %>">
                            Сократить
                        </button>
                    </form>
                    <% } else { %>
                    <form name="restore_position"
                          action="<%=ConstantAction.RESTORE_POSITION%>"
                          method="post">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                               value="<%=department.getId()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                               value="<%= true %>"/>
                        <button class="button-reduce"
                                name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                value="<%= position.getId() %>">
                            Восстановить
                        </button>
                    </form>
                    <% } %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.DEPARTMENT_INFO%>"
              method="get">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                   value="<%=department.getId()%>"/>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%= true %>"/>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
        <% } %>
        <div class="footer-buttons-block">
            <%if (isActual) { %>
            <form name="add_position"
                  action="<%=ConstantAction.ADD_POSITION%>"
                  method="get">
                <button class="footer-button, button-add"
                        type="submit"
                        name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                        value="<%=department.getId()%>">
                    Добавить новую должность
                </button>
            </form>
            <% } %>
            <form name="list_departments"
                  action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                  method="get">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=isActual%>"/>
                <button class="footer-button">
                    Вернуться к списку отделов
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
