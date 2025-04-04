<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.PassportDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменение паспортных данных</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<% PassportDTO passport = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT); %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите паспортные данные:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.UPDATE_PASSPORT%>"
          method="post">
        <input name="<%=ConstantParamAndAttribute.ID%>"
               type="hidden"
               value="<%= passport.getId() %>"
               required/>
        <div class="form-group">
            <label for="series">Серия:</label>
            <input type="text"
                   id="series"
                   name="<%=ConstantParamAndAttribute.SERIES%>"
                   value="<%= passport.getSeries() %>"
                   maxlength="2"
                   placeholder="Введите серию паспорта"
                   required/>
        </div>
        <div class="form-group">
            <label for="number">Номер:</label>
            <input type="text"
                   id="number"
                   name="<%=ConstantParamAndAttribute.NUMBER%>"
                   value="<%= passport.getNumber() %>"
                   placeholder="Введите номер паспорта"
                   required/>
        </div>
        <div class="form-group">
            <label for="identification_number">Идентификационный номер:</label>
            <input type="text"
                   id="identification_number"
                   name="<%=ConstantParamAndAttribute.IDENTIFICATION_NUMBER%>"
                   value="<%= passport.getIdentificationNumber() %>"
                   maxlength="14"
                   minlength="14"
                   placeholder="Введите идентификационный номер"
                   required/>
        </div>
        <div class="form-group">
            <label for="registration_city">Адрес регистрации(населенный пункт):</label>
            <input type="text"
                   id="registration_city"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_CITY%>"
                   value="<%= passport.getRegistrationAddress().getCity() %>"
                   placeholder="Введите название населенного пункта"
                   required/>
        </div>
        <div class="form-group">
            <label for="registration_street">Адрес регистрации(улица):</label>
            <input type="text"
                   id="registration_street"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_STREET%>"
                   value="<%= passport.getRegistrationAddress().getStreet() %>"
                   placeholder="Введите название улицы"
                   required/>
        </div>
        <div class="form-group">
            <label for="registration_house">Адрес регистрации(дом):</label>
            <input type="text"
                   id="registration_house"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_HOUSE%>"
                   value="<%= passport.getRegistrationAddress().getHouse() %>"
                   placeholder="Введите номер дома"
                   required/>
        </div>
        <div class="form-group">
            <label for="registration_apartment">Адрес регистрации(квартира):</label>
            <input type="text"
                   id="registration_apartment"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_APARTMENT%>"
                   <%if (passport.getRegistrationAddress().getApartment() != null) { %>
                   value="<%= passport.getRegistrationAddress().getApartment() %>"
                   <% } %>
                   placeholder="Введите номер квартиры"/>
        </div>
        <div class="form-group">
            <label for="date_issue">Дата выдачи:</label>
            <input type="date"
                   id="date_issue"
                   name="<%=ConstantParamAndAttribute.DATE_ISSUE%>"
                   value="<%= passport.getDateIssue() %>"
                   required/>
        </div>
        <div class="form-group">
            <label for="date_end_action">Дата окончания действия:</label>
            <input type="date"
                   id="date_end_action"
                   name="<%=ConstantParamAndAttribute.DATE_END_ACTION%>"
                   value="<%= passport.getDateEndAction() %>"
                   required/>
        </div>
        <div class="form-group">
            <label for="publisher">Кем выдан:</label>
            <input type="text"
                   id="publisher"
                   name="<%=ConstantParamAndAttribute.PUBLISHER%>"
                   value="<%= passport.getPublisher() %>"
                   placeholder="Введите название органа, выдавшего паспорт"
                   required/>
        </div>
    </form>
    <form class="tabs"
          action="<%=ConstantAction.PASSPORT%>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passport.getId()%>">
                Вернуться к просмотру паспортных данных
            </button>
        </div>
    </form>
</div>

</body>
</html>
