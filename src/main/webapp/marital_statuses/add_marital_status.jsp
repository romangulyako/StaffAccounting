<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Обновить семейное положение</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Заполните информацию о семейном положении:</h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_MARITAL_STATUS %>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_CURRENT%>"
                       value="true"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Семейное положение</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.STATUS %>"
                                   placeholder="Введите семейное положение"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата регистрации</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.REGISTRATION_DATE %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Подтверждающий документ</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.DOCUMENT %>"
                                   placeholder="Введите название и реквизиты документа"
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.MARITAL_STATUSES%>"
              method="get">
            <button class="footer-button, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру семейного положения
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
