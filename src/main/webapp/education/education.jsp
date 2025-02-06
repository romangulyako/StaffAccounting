<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("education", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2><%=LocalizationUtil.getMessage("education_of_employee", locale)%></h2>
        <% PageInfo<EducationDTO> pageInfo = (PageInfo<EducationDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<EducationDTO> education = pageInfo.getItems();
            if (education == null || education.isEmpty()) { %>
        <div class="form-group">
            <h3><%=LocalizationUtil.getMessage("education_absent", locale)%></h3>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th><%=LocalizationUtil.getMessage("education_level", locale)%></th>
                <th><%=LocalizationUtil.getMessage("institution", locale)%></th>
                <th><%=LocalizationUtil.getMessage("faculty", locale)%></th>
                <th><%=LocalizationUtil.getMessage("specialization", locale)%></th>
                <th><%=LocalizationUtil.getMessage("study_start", locale)%></th>
                <th><%=LocalizationUtil.getMessage("study_end", locale)%></th>
                <th colspan="2"><%=LocalizationUtil.getMessage("action", locale)%></th>
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
                            <%=LocalizationUtil.getMessage("edit_button", locale)%>
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
                            <%=LocalizationUtil.getMessage("delete_button", locale)%>
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
                    <%=LocalizationUtil.getMessage("add_education", locale)%>
                </button>
            </form>
            <form name="employee"
                  method="get"
                  action="<%= ConstantAction.EMPLOYEE %>">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                    <%=LocalizationUtil.getMessage("back_to_employee", locale)%>
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
