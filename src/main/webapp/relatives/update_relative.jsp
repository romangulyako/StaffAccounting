<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить родственника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<% RelativeDTO relative = (RelativeDTO) request.getAttribute(ConstantParamAndAttribute.RELATIVE); %>
<div class="form-container">
    <div class="form-group">
        <h2>Введите новые данные о родственнике:</h2>
    </div>
    <form id="save" action="<%= ConstantAction.UPDATE_RELATIVE%>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= relative.getId() %>"
               required/>
        <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
               type="hidden"
               value="<%= relative.getEmployeeId() %>"
               required/>
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text"
                   id="surname"
                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                   value="<%= relative.getPersonData().getSurname() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text"
                   id="name"
                   name="<%= ConstantParamAndAttribute.NAME %>"
                   value="<%= relative.getPersonData().getName() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="patronymic">Отчество:</label>
            <input type="text"
                   id="patronymic"
                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                   value="<%= relative.getPersonData().getPatronymic() %>">
        </div>
        <div class="form-group">
            <label for="birthday">Дата рождения:</label>
            <input type="date"
                   id="birthday"
                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                   value="<%= relative.getPersonData().getBirthday() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="type_kinship">Тип родства:</label>
            <input type="text"
                   id="type_kinship"
                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                   value="<%= relative.getTypeKinship()%>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%= ConstantAction.RELATIVES %>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=relative.getEmployeeId()%>">
                Вернуться к просмотру родственников
            </button>
        </div>
    </form>
</div>
</body>
</html>
