<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.entity.embedded.PersonData" %>
<%@ page import="by.itacademy.jd2.entity.embedded.Address" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<% EmployeeDTO employee = (EmployeeDTO) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE); %>
<% PersonData personData = employee.getPersonData(); %>
<% Address homeAddress = employee.getHomeAddress(); %>
<head>
    <title><%=personData.getSurname() + " "
            + personData.getName() + " " %>
        <%if (personData.getPatronymic() != null) {%>
        <%=personData.getPatronymic()%>
        <%}%>
    </title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp"%>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <div class="header-with-button">
            <h2>Информация о сотруднике</h2>
            <form name="update_employee"
                  method="get"
                  action="<%= ConstantAction.UPDATE_EMPLOYEE %>">
                <button class="button-edit"
                        name="<%= ConstantParamAndAttribute.ID %>"
                        value="<%= employee.getId() %>">
                    Изменить
                </button>
            </form>
        </div>
        <div class="information-block">
            <div class="information-item">
                <label>Фамилия:</label>
                <span><%=personData.getSurname()%></span>
            </div>
            <div class="information-item">
                <label>Имя:</label>
                <span><%=personData.getName()%></span>
            </div>
            <div class="information-item">
                <label>Отчество:</label>
                <span>
                <%if (personData.getPatronymic() != null) { %>
                <%=personData.getPatronymic()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label>Дата рождения:</label>
                <span><%=personData.getBirthday()%></span>
            </div>
            <div class="information-item">
                <label>Домашний адрес:</label>
                <span>
                <%=homeAddress.getCity()%>
                , ул. <%=homeAddress.getStreet()%>
                , д. <%=homeAddress.getHouse()%>
                <%if (homeAddress.getApartment() != null) { %>
                , кв. <%=homeAddress.getApartment()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label>Мобильный телефон:</label>
                <span>
                <%if (employee.getPhone() != null) { %>
                <%=employee.getPhone()%>
                <% } %>
            </span>
            </div>
        </div>
        <div class="footer-buttons-block">
            <form action="<%=ConstantAction.PASSPORT%>" method="get">
                <button class="footer-button" name="<%= ConstantParamAndAttribute.ID %>"
                        value="<%= employee.getId() %>">Паспортные данные
                </button>
            </form>
            <form action="<%=ConstantAction.RELATIVES%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">Родственники
                </button>
            </form>
            <form action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">Семейное положение
                </button>
            </form>
            <form action="<%=ConstantAction.EDUCATION%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">Образование
                </button>
            </form>
            <form action="<%=ConstantAction.CAREER%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">Карьера
                </button>
            </form>
        </div>
        <div class="footer-buttons-block">
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>" method="get">
                <button class="footer-button">Вернуться к списку сотрудников</button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp"%>
</body>
</html>
