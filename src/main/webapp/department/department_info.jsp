<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT); %>
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
        <form name="update_department"
              action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
              method="get">
            <button class="button-edit"
                    name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= department.getId() %>">
                Изменить
            </button>
        </form>
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
            <span><%=department.getDescription()%></span>
        </div>
    </div>
    <div class="form-group">
        <h2>Все должности структурного подразделения:</h2>
    </div>
    <div class="form-group">
        <% List<PositionDTO> positions = (List<PositionDTO>) request.getAttribute(ConstantParamAndAttribute.LIST_POSITIONS);
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
                    <th colspan="2">Действие</th>
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
                </tr>
                <%
                    }
                %>
            </table>
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
                <button class="tab">
                    Вернуться к списку отделов
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
