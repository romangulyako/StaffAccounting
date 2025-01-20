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
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Введите данные паспорта:</h2>
        <div class="filling-form">
            <% PassportDTO passport = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT); %>
            <form id="save"
                  action="<%=ConstantAction.ADD_PASSPORT%>"
                  method="post">
                <input name="<%=ConstantParamAndAttribute.ID%>"
                       type="hidden"
                       value="<%= passport.getId() %>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Серия</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SERIES%>"
                                   value="<%= passport.getSeries() %>"
                                   minlength="2"
                                   maxlength="2"
                                   placeholder="Введите серию паспорта"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Номер</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NUMBER%>"
                                   value="<%= passport.getNumber() %>"
                                   placeholder="Введите номер паспорта"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Идентификационный номер</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.IDENTIFICATION_NUMBER%>"
                                   value="<%= passport.getIdentificationNumber() %>"
                                   maxlength="14"
                                   minlength="14"
                                   placeholder="Введите идентификационный номер"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Адрес регистрации(населенный пункт)</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_CITY%>"
                                   value="<%= passport.getRegistrationAddress().getCity() %>"
                                   placeholder="Введите название населенного пункта"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Адрес регистрации(улица)</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_STREET%>"
                                   value="<%= passport.getRegistrationAddress().getStreet() %>"
                                   placeholder="Введите название улицы"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Адрес регистрации(дом)</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_HOUSE%>"
                                   value="<%= passport.getRegistrationAddress().getHouse() %>"
                                   placeholder="Введите номер дома"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Адрес регистрации(квартира)</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_APARTMENT%>"
                                    <%if (passport.getRegistrationAddress().getApartment() != null) { %>
                                   value="<%= passport.getRegistrationAddress().getApartment() %>"
                                    <% } %>
                                   placeholder="Введите номер квартиры"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата выдачи</legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_ISSUE%>"
                                   value="<%= passport.getDateIssue() %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата окончания действия</legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_END_ACTION%>"
                                   value="<%= passport.getDateEndAction() %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Кем выдан</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.PUBLISHER%>"
                                   value="<%= passport.getPublisher() %>"
                                   placeholder="Введите название органа, выдавшего паспорт"
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.PASSPORT%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save">
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passport.getId()%>">
                Вернуться к просмотру паспортных данных
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
