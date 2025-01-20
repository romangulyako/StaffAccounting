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
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Заполните информацию о сотруднике:</h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_EMPLOYEE %>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Фамилия</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                                   placeholder="Введите фамилию"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Имя</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   placeholder="Введите имя"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Отчество</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                                   placeholder="Введите отчество"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата рождения</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Город, в котором проживает:</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_CITY %>"
                                   placeholder="Введите название города"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Улица, на которой проживает:</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_STREET %>"
                                   placeholder="Введите название улицы"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дом, в котором проживает:</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_HOUSE %>"
                                   placeholder="Введите номер дома"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Квартира, в которой проживает</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_APARTMENT %>"
                                   placeholder="Введите номер квартиры"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Номер телефона</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PHONE %>"
                                   placeholder="Введите номер телефона"/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.LIST_EMPLOYEES%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save"
                    type="submit">
                Сохранить
            </button>
            <button class="footer-button">
                Вернуться к просмотру сотрудника
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
