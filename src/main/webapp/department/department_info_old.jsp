<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT);
    Boolean isActual = (Boolean) request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL); %>
<head>
    <title><%=department.getName()%>
    </title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container, general-div">
    <div class="item-header">
        <h2>Информация об отделе:</h2>
        <%if (!isActual) { %>
        <form name="update_department"
              action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
              method="get">
            <button class="button-edit"
                    name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= department.getId() %>">
                Изменить
            </button>
        </form>
        <% } %>
    </div>
    <div class="item-info">
        <div class="item-info-item">
            <label>Наименование:</label>
            <span><%=department.getName()%></span>
        </div>
        <div class="item-info-item">
            <label>Наименование в родительном падеже:</label>
            <span><%=department.getGenitiveCaseName()%></span>
        </div>
        <div class="item-info-item">
            <label>Описание:</label>
            <span>
                <%if (department.getDescription() != null) { %>
                <%=department.getDescription()%>
                <% } %>
            </span>
        </div>
    </div>
    <div class="form-group">
        <h2>Все должности структурного подразделения:</h2>
    </div>
    <div class="form-group">
        <% PageInfo<PositionDTO> pageInfo = (PageInfo<PositionDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<PositionDTO> positions = pageInfo.getItems();
            if (positions == null || positions.isEmpty()) { %>
        <div class="form-group">
            <h3>В отделе пока нет должностей</h3>
        </div>
        <% } else { %>
        <div class="form-group">
            <table>
                <tr>
                    <th>Наименование должности</th>
                    <th>Необходимый уровень образования</th>
                    <th>Оклад по должности</th>
                    <th colspan="4">Действие</th>
                </tr>
                <tr>
                    <% for (PositionDTO position : positions) { %>
                    <td><%=position.getName()%>
                    </td>
                    <td><%=position.getEducationLevel()%>
                    </td>
                    <td><%=position.getSalary()%>
                    </td>
                    <td>
                        <form name="update_position"
                              method="get"
                              action="<%= ConstantAction.UPDATE_POSITION %>">
                            <button class="button-edit"
                                    name="<%= ConstantParamAndAttribute.ID %>"
                                    value="<%= position.getId() %>">
                                Изменить
                            </button>
                        </form>
                    </td>
                    <td>
                        <form name="delete_position"
                              method="post"
                              action="<%= ConstantAction.DELETE_POSITION %>">
                            <button class="button-delete"
                                    name="<%= ConstantParamAndAttribute.ID %>"
                                    value="<%= position.getId() %>">
                                Удалить
                            </button>
                            <input type="hidden"
                                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                                   value="<%=department.getId()%>">
                        </form>
                    </td>
                    <td>
                        <form name="show_history"
                              method="get"
                              action="<%= ConstantAction.POSITION_HISTORY %>">
                            <button class="button-show"
                                    name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                    value="<%= position.getId() %>">
                                Посмотреть историю
                            </button>
                            <input type="hidden"
                                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                                   value="<%=department.getId()%>">
                        </form>
                    </td>
                    <td>
                        <%if (isActual == null || isActual) {%>
                        <form name="reduce_position"
                              method="post"
                              action="<%= ConstantAction.REDUCE_POSITION %>">
                            <button class="button-reduce"
                                    name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                    value="<%= position.getId() %>">
                                Сократить
                            </button>
                            <input type="hidden"
                                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                                   value="<%=department.getId()%>">
                        </form>
                        <% } else {%>
                        <form name="restore_position"
                              method="post"
                              action="<%= ConstantAction.RESTORE_POSITION %>">
                            <button class="button-restore"
                                    name="<%= ConstantParamAndAttribute.POSITION_ID %>"
                                    value="<%= position.getId() %>">
                                Восстановить
                            </button>
                            <input type="hidden"
                                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                                   value="<%=department.getId()%>">
                        </form>
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <form action="<%=ConstantAction.DEPARTMENT_INFO%>"
                  method="get">
                <%if (Boolean.FALSE.equals(isActual)) { %>
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=isActual%>"/>
                <% } %>
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                       value="<%= department.getId()%>"/>
                <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                    request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                    request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
                <%@include file="../paginator.jsp" %>
            </form>
        </div>
        <%
            }
        %>
        <div class="tabs">
            <form name="add_position"
                  action="<%=ConstantAction.ADD_POSITION%>"
                  method="get">
                <button class="tab, button-add"
                        type="submit"
                        name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                        value="<%=department.getId()%>">
                    Добавить новую должность
                </button>
            </form>
            <form name="list_departments"
                  action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                  method="get">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=isActual%>"/>
                <button class="tab">
                    Вернуться к списку отделов
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
