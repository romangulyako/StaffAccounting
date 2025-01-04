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
<div class="form-container">
    <div class="form-group">
        <h2>Заполните информацию:</h2>
    </div>
    <form id="save"
          action="<%= ConstantAction.ADD_MARITAL_STATUS %>"
          method="post">
        <div class="form-group">
            <label for="marital_status">Семейное положение:</label>
            <input type="text"
                   id="marital_status"
                   name="<%= ConstantParamAndAttribute.STATUS %>"
                   required>
        </div>
        <div class="form-group">
            <label for="registration_date">Дата регистрации:</label>
            <input type="date"
                   id="registration_date"
                   name="<%= ConstantParamAndAttribute.REGISTRATION_DATE %>"
                   required>
        </div>
        <div class="form-group">
            <label for="document">Подтверждающий документ:</label>
            <input type="text"
                   id="document"
                   name="<%= ConstantParamAndAttribute.DOCUMENT %>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Сохранить
            </button>
            <input form="save"
                   type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_CURRENT%>"
                   value="true"
                   required/>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру семейного положения
            </button>
        </div>
    </form>
</div>
</body>
</html>
