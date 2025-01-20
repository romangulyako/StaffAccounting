<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Образование</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2>Образование сотрудника</h2>
        <% PageInfo<EducationDTO> pageInfo = (PageInfo<EducationDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<EducationDTO> education = pageInfo.getItems();
            if (education == null || education.isEmpty()) { %>
        <div class="form-group">
            <h3>Информация об образовании отсутствует</h3>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>Уровень образования</th>
                <th>Учреждение образования</th>
                <th>Факультет</th>
                <th>Специальность</th>
                <th>Дата начала обучения</th>
                <th>Дата окончания обучения</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (EducationDTO dto : education) { %>
                <td><%= dto.getEducationLevel() %>
                </td>
                <td><%= dto.getInstitution() %>
                </td>
                <td>
                    <%if (dto.getFaculty() != null) { %>
                    <%= dto.getFaculty() %>
                    <% }%>
                </td>
                <td>
                    <%if (dto.getSpecialization() != null) { %>
                    <%= dto.getSpecialization() %>
                    <% }%>
                </td>
                <td><%= dto.getDateStart() %>
                </td>
                <td><%= dto.getDateEnd() %>
                </td>
                <td>
                    <form name="update_education"
                          method="get"
                          action="<%= ConstantAction.UPDATE_EDUCATION %>">
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= dto.getId() %>">
                            Изменить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= dto.getEmployeeId() %>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_education"
                          method="post"
                          action="<%= ConstantAction.DELETE_EDUCATION %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= dto.getId() %>">
                            Удалить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= dto.getEmployeeId() %>"/>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.EDUCATION%>"
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
            <form name="add_education"
                  method="get"
                  action="<%= ConstantAction.ADD_EDUCATION %>">
                <button class="footer-button, button-add"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                    Добавить образование
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
