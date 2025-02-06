<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.CareerStepGetDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.CareerStepGetDTO" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page import="java.util.Locale" %>
<%@ page import="by.itacademy.jd2.utils.LocalizationUtil" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <%Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("career", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2><%=LocalizationUtil.getMessage("career_header", locale)%></h2>
        <%
            PageInfo<CareerStepGetDTO> pageInfo = (PageInfo<CareerStepGetDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<CareerStepGetDTO> career = pageInfo.getItems();
            if (career == null || career.isEmpty()) {
        %>
        <div class="form-group">
            <h3><%=LocalizationUtil.getMessage("career_absent", locale)%></h3>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th><%=LocalizationUtil.getMessage("appointment_order", locale)%></th>
                <th><%=LocalizationUtil.getMessage("from_what_date", locale)%></th>
                <th><%=LocalizationUtil.getMessage("on_what_date", locale)%></th>
                <th><%=LocalizationUtil.getMessage("position", locale)%></th>
                <th colspan="2"><%=LocalizationUtil.getMessage("action", locale)%></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (CareerStepGetDTO careerStep : career) { %>
                <td><%=careerStep.getOrderAppointment()%>
                </td>
                <td><%=careerStep.getDateOfAppointment()%>
                </td>
                <td>
                    <% if (careerStep.getDateOfLiberation() == null) { %>
                    <%=LocalizationUtil.getMessage("today", locale)%>
                    <% } else { %>
                    <%=careerStep.getDateOfLiberation()%>
                    <%}%>
                </td>
                <td><%=careerStep.getPositionName() + " " + careerStep.getDepartmentGenitiveCaseName()%>
                </td>
                <td>
                    <form name="update_career_step"
                          method="get"
                          action="<%= ConstantAction.UPDATE_CAREER_STEP %>">
                        <button class="button-edit">
                            <%=LocalizationUtil.getMessage("edit_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.CAREER_ID%>"
                               value="<%=careerStep.getId()%>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_career_step"
                          method="post"
                          action="<%= ConstantAction.DELETE_CAREER_STEP %>">
                        <button class="button-delete"
                                name="<%=ConstantParamAndAttribute.CAREER_ID%>"
                                value="<%=careerStep.getId()%>">
                            <%=LocalizationUtil.getMessage("delete_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%=careerStep.getEmployeeId()%>"/>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.CAREER%>"
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
        <form class="footer-buttons-block"
              name="employee"
              method="get"
              action="<%= ConstantAction.EMPLOYEE %>">
            <button class="footer-button"
                    name="<%= ConstantParamAndAttribute.ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("back_to_employee", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
