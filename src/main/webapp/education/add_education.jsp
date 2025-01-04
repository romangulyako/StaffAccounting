<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить образование</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные об образовании:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.ADD_EDUCATION%>"
          method="post">
        <div class="form-group">
            <label for="educationLevel">Уровень образования:</label>
            <input type="text" id="educationLevel" name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"/>
        </div>
        <div class="form-group">
            <label for="institution">Учебное заведение:</label>
            <input type="text" id="institution" name="<%=ConstantParamAndAttribute.INSTITUTION%>"/>
        </div>
        <div class="form-group">
            <label for="faculty">Факультет:</label>
            <input type="text" id="faculty" name="<%=ConstantParamAndAttribute.FACULTY%>"/>
        </div>
        <div class="form-group">
            <label for="specialization">Специализация:</label>
            <input type="text" id="specialization" name="<%=ConstantParamAndAttribute.SPECIALIZATION%>"/>
        </div>
        <div class="form-group">
            <label for="dateStart">Дата начала обучения:</label>
            <input type="date" id="dateStart" name="<%=ConstantParamAndAttribute.DATE_START%>"/>
        </div>
        <div class="form-group">
            <label for="dateEnd">Дата окончания обучения:</label>
            <input type="date" id="dateEnd" name="<%=ConstantParamAndAttribute.DATE_END%>"/>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.EDUCATION%>" method="get">
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
                Вернуться к просмотру образования
            </button>
        </div>
    </form>
</div>
</body>
</html>
