<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить должность</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите данные о должности:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.ADD_POSITION%>"
          method="post">
        <div class="form-group">
            <label for="name">Наименование должности:</label>
            <input type="text"
                   id="name"
                   name="<%=ConstantParamAndAttribute.NAME%>"
                   required>
        </div>
        <div class="form-group">
            <label for="education_level">Необходимый уровень образования:</label>
            <input type="text"
                   id="education_level"
                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                   required>
        </div>
        <div class="form-group">
            <label for="salary">Денежный оклад по должности:</label>
            <input type="text"
                   id="salary"
                   name="<%=ConstantParamAndAttribute.SALARY%>"
                   required>
        </div>
    </form>
    <form class="tabs"
          action="<%=ConstantAction.DEPARTMENT_INFO%>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add"
                    form="save"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                Вернуться к просмотру информации об отделе
            </button>
        </div>
    </form>
</div>
</body>
</html>
