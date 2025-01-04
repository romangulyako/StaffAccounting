<%@ page import="by.itacademy.jd2.dto.MaritalStatusDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить семейное положение</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<% MaritalStatusDTO maritalStatus = (MaritalStatusDTO) request.getAttribute(ConstantParamAndAttribute.MARITAL_STATUS); %>
<div class="form-container">
    <div class="form-group">
        <h2>Заполните информацию:</h2>
    </div>
    <form id="save"
          action="<%= ConstantAction.UPDATE_MARITAL_STATUS %>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= maritalStatus.getId() %>"
               required/>
        <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
               type="hidden"
               value="<%= maritalStatus.getEmployeeId() %>"
               required/>
        <input name="<%= ConstantParamAndAttribute.IS_CURRENT %>"
               type="hidden"
               value="<%= maritalStatus.isCurrent() %>"
               required/>
        <div class="form-group">
            <label for="marital_status">Семейное положение:</label>
            <input type="text"
                   id="marital_status"
                   name="<%= ConstantParamAndAttribute.STATUS %>"
                   value="<%= maritalStatus.getStatus() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="registration_date">Дата регистрации:</label>
            <input type="date"
                   id="registration_date"
                   name="<%= ConstantParamAndAttribute.REGISTRATION_DATE %>"
                   value="<%= maritalStatus.getRegistrationDate() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="document">Подтверждающий документ:</label>
            <input type="text"
                   id="document"
                   name="<%= ConstantParamAndAttribute.DOCUMENT %>"
                   value="<%= maritalStatus.getDocument() %>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%= maritalStatus.getEmployeeId() %>">
                Вернуться к просмотру семейного положения
            </button>
        </div>
    </form>
</div>
</body>
</html>
