<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменение сотрудника</title>
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
            <% EmployeeDTO employee = (EmployeeDTO) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE); %>
            <form id="save"
                  action="<%= ConstantAction.UPDATE_EMPLOYEE %>"
                  method="post">
                <input name="<%= ConstantParamAndAttribute.ID %>"
                       type="hidden"
                       value="<%= employee.getId() %>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Фамилия</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                                   placeholder="Введите фамилию"
                                   value="<%=employee.getPersonData().getSurname()%>"
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
                                   value="<%=employee.getPersonData().getName()%>"
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
                                    <%if (employee.getPersonData().getPatronymic() != null) { %>
                                   value="<%= employee.getPersonData().getPatronymic() %>"
                                    <% } %>
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
                                   value="<%= employee.getPersonData().getBirthday() %>"
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
                                   value="<%= employee.getHomeAddress().getCity() %>"
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
                                   value="<%= employee.getHomeAddress().getStreet() %>"
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
                                   value="<%= employee.getHomeAddress().getHouse() %>"
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
                                    <%if (employee.getHomeAddress().getApartment() != null) { %>
                                   value="<%= employee.getHomeAddress().getApartment() %>"
                                    <% } %>
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
                                    <%if (employee.getPhone() != null) { %>
                                   value="<%= employee.getPhone() %>"
                                    <% } %>
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
