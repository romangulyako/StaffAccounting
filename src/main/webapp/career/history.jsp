<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.PositionHistoryDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История должности</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>История должности:</h2>
    </div>
    <% List<PositionHistoryDTO> history = (List<PositionHistoryDTO>) request.getAttribute(ConstantParamAndAttribute.HISTORY);
        if (history == null || history.isEmpty()) { %>
    <div class="form-group">
        <h3>История должности пуста</h3>
    </div>
    <% } else { %>
    <div class="form-group">
        <table>
            <tr>
                <th>Дата назначения</th>
                <th>Приказ о назначении</th>
                <th>ФИО сотрудника</th>
                <th>Дата освобождения должности</th>
            </tr>
            <tr>
                <% for (PositionHistoryDTO historyItem : history) { %>
                <td><%=historyItem.getDateOfAppointment()%>
                </td>
                <td><%=historyItem.getOrderAppointment()%>
                </td>
                <td><%=historyItem.getEmployeeSurname() + " "
                + historyItem.getEmployeeName() + " "
                + historyItem.getEmployeePatronymic()%>
                </td>
                <td><%=historyItem.getDateOfLiberation()%>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
    <% } %>
    <div class="tabs">
        <form name="department_info"
              method="get"
              action="<%= ConstantAction.DEPARTMENT_INFO %>">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                Вернуться к просмотру отдела
            </button>
        </form>
    </div>
</div>
</body>
</html>
