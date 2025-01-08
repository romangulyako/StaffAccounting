<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeItemDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.PositionItemDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Назначение на должность</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Заполните форму для назначения сотрудника:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.APPOINTMENT%>"
          method="post">
        <div class="form-group">
            <label for="employee">Выберите сотрудника</label>
            <select name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>" id="employee" required>
                <option value="" disabled selected>Выберите сотрудника</option>
                <% List<EmployeeItemDTO> employees = (List<EmployeeItemDTO>) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ITEMS);
                    if (employees != null) {
                        for (EmployeeItemDTO employee : employees) {
                %>
                <option value="<%=employee.getId()%>"><%=employee.getFullName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="position">Выберите должность</label>
            <select name="<%=ConstantParamAndAttribute.NEW_POSITION_ID%>" id="position" required>
                <option value="" disabled selected>Выберите должность</option>
                <% List<PositionItemDTO> positions = (List<PositionItemDTO>) request.getAttribute(ConstantParamAndAttribute.POSITION_ITEMS);
                    if (positions != null) {
                        for (PositionItemDTO position : positions) {
                %>
                <option value="<%=position.getId()%>"><%=position.getFullName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="date_of_appointment">Дата назначения:</label>
            <input type="date" id="date_of_appointment" name="<%= ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT %>" required>
        </div>
        <div class="form-group">
            <label for="order">Приказ о назначении:</label>
            <input type="text" id="order" name="<%= ConstantParamAndAttribute.ORDER %>">
        </div>
    </form>
    <form class="tabs">
        <div class="form-group">
            <button class="button-add"
                    form="save"
                    type="submit">
                Назначить
            </button>
        </div>
    </form>
</div>
</body>
</html>
