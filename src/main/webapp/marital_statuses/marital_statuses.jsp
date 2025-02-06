<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.MaritalStatusDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("marital_status", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2><%=LocalizationUtil.getMessage("marital_info", locale)%></h2>
        <% PageInfo<MaritalStatusDTO> pageInfo = (PageInfo<MaritalStatusDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<MaritalStatusDTO> maritalStatuses = pageInfo.getItems();
            if (maritalStatuses == null || maritalStatuses.isEmpty()) { %>
        <h3><%=LocalizationUtil.getMessage("marital_info_absent", locale)%></h3>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th><%=LocalizationUtil.getMessage("marital_status", locale)%></th>
                <th><%=LocalizationUtil.getMessage("registration_date", locale)%></th>
                <th><%=LocalizationUtil.getMessage("document", locale)%></th>
                <th colspan="2"><%=LocalizationUtil.getMessage("action", locale)%></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (MaritalStatusDTO maritalStatus : maritalStatuses) { %>
                <td><%= maritalStatus.getStatus() %>
                </td>
                <td><%= maritalStatus.getRegistrationDate() %>
                </td>
                <td><%= maritalStatus.getDocument() %>
                </td>
                <td>
                    <form name="update_marital_status"
                          method="get"
                          action="<%= ConstantAction.UPDATE_MARITAL_STATUS %>">
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= maritalStatus.getId() %>">
                            <%=LocalizationUtil.getMessage("edit_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= maritalStatus.getEmployeeId() %>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_marital_status"
                          method="post"
                          action="<%= ConstantAction.DELETE_MARITAL_STATUS %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= maritalStatus.getId() %>">
                            <%=LocalizationUtil.getMessage("delete_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= maritalStatus.getEmployeeId() %>"/>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.MARITAL_STATUSES%>"
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
            <form name="add_marital_status"
                  method="get"
                  action="<%= ConstantAction.ADD_MARITAL_STATUS %>">
                <button class="footer-button, button-add"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID) %>">
                    <%=LocalizationUtil.getMessage("update_marital_status", locale)%>
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
