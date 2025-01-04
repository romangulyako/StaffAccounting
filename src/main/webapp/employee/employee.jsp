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
            + personData.getName() + " "
            + personData.getPatronymic() %>
    </title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<div class="container">
    <div class="item-header">
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
    <div class="item-info">
        <div class="item-info-item">
            <label>Фамилия:</label>
            <span><%=personData.getSurname()%></span>
        </div>
        <div class="item-info-item">
            <label>Имя:</label>
            <span><%=personData.getName()%></span>
        </div>
        <div class="item-info-item">
            <label>Отчество:</label>
            <span><%=personData.getPatronymic()%></span>
        </div>
        <div class="item-info-item">
            <label>Дата рождения:</label>
            <span><%=personData.getBirthday()%></span>
        </div>
        <div class="item-info-item">
            <label>Домашний адрес:</label>
            <span><%=homeAddress.getCity()%>, ул. <%=homeAddress.getStreet()%>, д. <%=homeAddress.getHouse()%>, кв. <%=homeAddress.getApartment()%></span>
        </div>
        <div class="item-info-item">
            <label>Мобильный телефон:</label>
            <span><%=employee.getPhone()%></span>
        </div>
    </div>

    <div class="tabs">
        <form action="<%=ConstantAction.PASSPORT%>" method="get">
            <button class="tab" name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= employee.getId() %>">Паспортные данные
            </button>
        </form>
        <form action="<%=ConstantAction.RELATIVES%>" method="get">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                    value="<%= employee.getId() %>">Родственники
            </button>
        </form>
        <form action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                    value="<%= employee.getId() %>">Семейное положение
            </button>
        </form>
        <form action="education" method="post">
            <button class="tab">Образование</button>
        </form>
        <form action="career" method="post">
            <button class="tab">Карьера</button>
        </form>
    </div>
    <div class="tabs">
        <form action="<%=ConstantAction.LIST_EMPLOYEES%>" method="get">
            <button class="tab">Вернуться к списку сотрудников</button>
        </form>
    </div>
</div>
</body>
</html>
