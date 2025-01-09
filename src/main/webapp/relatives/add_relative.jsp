<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавить родственника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные о родственнике:</h2>
    </div>
    <form id="save" action="<%=ConstantAction.ADD_RELATIVE%>"
          method="post">
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
            <label for="type_kinship">Тип родства:</label>
            <input type="text"
                   id="type_kinship"
                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                   placeholder="Введите тип родства"
                   required/>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.RELATIVES%>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру родственников
            </button>
        </div>
    </form>
</div>
</body>
</html>
