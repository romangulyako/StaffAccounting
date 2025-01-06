<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить паспорт</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные о паспорте:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.ADD_PASSPORT%>"
          method="post">
        <div class="form-group">
            <label for="series">Серия:</label>
            <input type="text"
                   id="series"
                   name="<%=ConstantParamAndAttribute.SERIES%>"
                   maxlength="2"
                   required>
        </div>
        <div class="form-group">
            <label for="number">Номер:</label>
            <input type="text"
                   id="number"
                   name="<%=ConstantParamAndAttribute.NUMBER%>"
                   required>
        </div>
        <div class="form-group">
            <label for="identification_number">Идентификационный номер:</label>
            <input type="text"
                   id="identification_number"
                   name="<%=ConstantParamAndAttribute.IDENTIFICATION_NUMBER%>"
                   maxlength="14"
                   required>
        </div>
        <div class="form-group">
            <label for="registration_city">Адрес регистрации(населенный пункт):</label>
            <input type="text"
                   id="registration_city"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_CITY%>">
        </div>
        <div class="form-group">
            <label for="registration_street">Адрес регистрации(улица):</label>
            <input type="text"
                   id="registration_street"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_STREET%>">
        </div>
        <div class="form-group">
            <label for="registration_house">Адрес регистрации(дом):</label>
            <input type="text"
                   id="registration_house"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_HOUSE%>">
        </div>
        <div class="form-group">
            <label for="registration_apartment">Адрес регистрации(квартира):</label>
            <input type="text"
                   id="registration_apartment"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_APARTMENT%>">
        </div>
        <div class="form-group">
            <label for="date_issue">Дата выдачи:</label>
            <input type="date"
                   id="date_issue"
                   name="<%=ConstantParamAndAttribute.DATE_ISSUE%>"
                   required>
        </div>
        <div class="form-group">
            <label for="date_end_action">Дата окончания действия:</label>
            <input type="date"
                   id="date_end_action"
                   name="<%=ConstantParamAndAttribute.DATE_END_ACTION%>"
                   required>
        </div>
        <div class="form-group">
            <label for="publisher">Кем выдан:</label>
            <input type="text"
                   id="publisher"
                   name="<%=ConstantParamAndAttribute.PUBLISHER%>"
                   required>
        </div>
    </form>
    <form class="tabs"
          action="<%=ConstantAction.PASSPORT%>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Вернуться к просмотру паспортных данных
            </button>
        </div>
    </form>
</div>
</body>
</html>
