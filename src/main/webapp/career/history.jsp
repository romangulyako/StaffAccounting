<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.PositionHistoryDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>История должности</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2>История должности:</h2>
        <% PageInfo<PositionHistoryDTO> pageInfo = (PageInfo<PositionHistoryDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
            List<PositionHistoryDTO> history = pageInfo.getItems();
            if (history == null || history.isEmpty()) { %>
        <div class="form-group">
            <h3>История этой должности отсутствует</h3>
        </div>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>С какого числа</th>
                <th>По какое число</th>
                <th>ФИО сотрудника</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (PositionHistoryDTO historyItem : history) { %>
                <td><%=historyItem.getDateOfAppointment()%>
                </td>
                <td>
                    <%if (historyItem.getDateOfLiberation() != null) { %>
                    <%=historyItem.getDateOfLiberation()%>
                    <% } else { %>
                    н.вр.
                    <% } %>
                </td>
                <td><%=historyItem.getEmployeeSurname() + " "
                        + historyItem.getEmployeeName()%>
                    <%if (historyItem.getEmployeePatronymic() != null) { %>
                    <%= " " + historyItem.getEmployeePatronymic()%>
                    <% } %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.POSITION_HISTORY%>"
              method="get">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                   value="<%= request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>"/>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.POSITION_ID%>"
                   value="<%= request.getAttribute(ConstantParamAndAttribute.POSITION_ID)%>"/>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
        <% } %>
        <form class="footer-buttons-block"
              name="department_info"
              method="get"
              action="<%= ConstantAction.DEPARTMENT_INFO %>">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
            <button class="footer-button"
                    name="<%= ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                Вернуться к просмотру отдела
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
