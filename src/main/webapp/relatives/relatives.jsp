<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("relatives", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2><%=LocalizationUtil.getMessage("relatives_info", locale)%></h2>
        <% PageInfo<RelativeDTO> pageInfo = (PageInfo<RelativeDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<RelativeDTO> relatives = pageInfo.getItems();
            if (relatives == null || relatives.isEmpty()) { %>
        <h3><%=LocalizationUtil.getMessage("relatives_info_absent", locale)%></h3>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th><%=LocalizationUtil.getMessage("surname", locale)%></th>
                <th><%=LocalizationUtil.getMessage("name", locale)%></th>
                <th><%=LocalizationUtil.getMessage("patronymic", locale)%></th>
                <th><%=LocalizationUtil.getMessage("birthday", locale)%></th>
                <th><%=LocalizationUtil.getMessage("type_kinship", locale)%></th>
                <th colspan="2"><%=LocalizationUtil.getMessage("action", locale)%></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (RelativeDTO relative : relatives) { %>
                <td><%= relative.getPersonData().getSurname() %>
                </td>
                <td><%= relative.getPersonData().getName() %>
                </td>
                <td>
                    <%if (relative.getPersonData().getPatronymic() != null) { %>
                    <%= relative.getPersonData().getPatronymic() %>
                    <% } %>
                </td>
                <td><%= relative.getPersonData().getBirthday() %>
                </td>
                <td><%= relative.getTypeKinship() %>
                </td>
                <td>
                    <form name="update_relative"
                          method="get"
                          action="<%= ConstantAction.UPDATE_RELATIVE %>">
                        <button class="button-edit"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= relative.getId() %>">
                            <%=LocalizationUtil.getMessage("edit_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= relative.getEmployeeId() %>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_relative"
                          method="post"
                          action="<%= ConstantAction.DELETE_RELATIVE %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= relative.getId() %>">
                            <%=LocalizationUtil.getMessage("delete_button", locale)%>
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%= relative.getEmployeeId() %>"/>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.RELATIVES%>"
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
            <form name="add_relative"
                  method="get"
                  action="<%= ConstantAction.ADD_RELATIVE %>">
                <button class="footer-button, button-add"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID%>"
                        value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                    <%=LocalizationUtil.getMessage("add_relative", locale)%>
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
