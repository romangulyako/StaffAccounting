<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение сотрудника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<% EmployeeDTO employee = (EmployeeDTO) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE); %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные о сотруднике:</h2>
    </div>
    <form id="save"
          action="<%= ConstantAction.UPDATE_EMPLOYEE %>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= employee.getId() %>"
               required>
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text"
                   id="surname"
                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                   value="<%= employee.getPersonData().getSurname() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text"
                   id="name"
                   name="<%= ConstantParamAndAttribute.NAME %>"
                   value="<%= employee.getPersonData().getName() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="patronymic">Отчество:</label>
            <input type="text"
                   id="patronymic"
                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                   value="<%= employee.getPersonData().getPatronymic() %>">
        </div>
        <div class="form-group">
            <label for="birthday">Дата рождения:</label>
            <input type="date"
                   id="birthday"
                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                   value="<%= employee.getPersonData().getBirthday() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="residence_city">Город, в котором проживает:</label>
            <input type="text"
                   id="residence_city"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_CITY %>"
                   value="<%= employee.getHomeAddress().getCity() %>">
        </div>
        <div class="form-group">
            <label for="residence_street">Улица, на которой проживает:</label>
            <input type="text"
                   id="residence_street"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_STREET %>"
                   value="<%= employee.getHomeAddress().getStreet() %>">
        </div>
        <div class="form-group">
            <label for="residence_house">Дом, в котором проживает:</label>
            <input type="text"
                   id="residence_house"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_HOUSE %>"
                   value="<%= employee.getHomeAddress().getHouse() %>">
        </div>
        <div class="form-group">
            <label for="residence_apartment">Квартира, в которой проживает:</label>
            <input type="text"
                   id="residence_apartment"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_APARTMENT %>"
                   value="<%= employee.getHomeAddress().getApartment() %>">
        </div>
        <div class="form-group">
            <label for="phone">Номер телефона:</label>
            <input type="text"
                   id="phone"
                   name="<%= ConstantParamAndAttribute.PHONE %>"
                   value="<%= employee.getPhone() %>">
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.LIST_EMPLOYEES%>" method="get">
        <div class="form-group">
            <button class="tab, button-add"
                    form="save"
                    type="submit">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab">
                Вернуться к просмотру сотрудника
            </button>
        </div>
    </form>
</div>
</body>
</html>
