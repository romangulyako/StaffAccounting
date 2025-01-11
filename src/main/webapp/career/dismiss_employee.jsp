<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Увольнение сотрудника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Заполните форму для удаления сотрудника:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.DISMISS%>"
          method="post">
        <div class="form-group">
            <label for="employee">Выберите сотрудника</label>
            <select name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    id="employee"
                    required>
                <option value=""
                        disabled
                        selected>
                    Выберите сотрудника
                </option>
                <% List<EmployeeItemDTO> employees = (List<EmployeeItemDTO>) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ITEMS);
                    if (employees != null) {
                        for (EmployeeItemDTO employee : employees) {
                %>
                <option value="<%=employee.getId()%>">
                    <%=employee.getSurname() + " " + employee.getName() + " " + employee.getPatronymic()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="date_of_dismiss">Дата увольнения:</label>
            <input type="date"
                   id="date_of_dismiss"
                   name="<%= ConstantParamAndAttribute.DATE_DISMISS %>"
                   required>
        </div>
        <div class="form-group">
            <label for="order">Приказ об увольнении:</label>
            <input type="text"
                   id="order"
                   name="<%= ConstantParamAndAttribute.ORDER_DISMISS %>"
                   required>
        </div>
    </form>
    <form class="tabs">
        <div class="form-group">
            <button class="button-add"
                    form="save"
                    type="submit">
                Уволить
            </button>
        </div>
    </form>
</div>
</body>
</html>
