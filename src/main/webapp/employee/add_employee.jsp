<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление сотрудника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные о новом сотруднике:</h2>
    </div>
    <form id="save" action="<%= ConstantAction.ADD_EMPLOYEE %>" method="post">
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text"
                   id="surname"
                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                   placeholder="Введите фамилию"
                   required/>
        </div>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text"
                   id="name"
                   name="<%= ConstantParamAndAttribute.NAME %>"
                   placeholder="Введите имя"
                   required/>
        </div>
        <div class="form-group">
            <label for="patronymic">Отчество:</label>
            <input type="text"
                   id="patronymic"
                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                   placeholder="Введите отчество"/>
        </div>
        <div class="form-group">
            <label for="birthday">Дата рождения:</label>
            <input type="date"
                   id="birthday"
                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                   required/>
        </div>
        <div class="form-group">
            <label for="residence_city">Город, в котором проживает:</label>
            <input type="text"
                   id="residence_city"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_CITY %>"
                   placeholder="Введите название города"
                   required/>
        </div>
        <div class="form-group">
            <label for="residence_street">Улица, на которой проживает:</label>
            <input type="text"
                   id="residence_street"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_STREET %>"
                   placeholder="Введите название улицы"
                   required/>
        </div>
        <div class="form-group">
            <label for="residence_house">Дом, в котором проживает:</label>
            <input type="text"
                   id="residence_house"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_HOUSE %>"
                   placeholder="Введите номер дома"
                   required/>
        </div>
        <div class="form-group">
            <label for="residence_apartment">Квартира, в которой проживает:</label>
            <input type="text"
                   id="residence_apartment"
                   name="<%= ConstantParamAndAttribute.RESIDENCE_APARTMENT %>"
                   placeholder="Введите номер квартиры"/>
        </div>
        <div class="form-group">
            <label for="phone">Номер телефона:</label>
            <input type="text"
                   id="phone"
                   name="<%= ConstantParamAndAttribute.PHONE %>"
                   placeholder="Введите номер телефона"/>
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
