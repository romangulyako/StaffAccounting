<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить должность</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<% PositionDTO position = (PositionDTO) request.getAttribute(ConstantParamAndAttribute.POSITION);%>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите новые данные о должности:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.UPDATE_POSITION%>"
          method="post">
        <input name="<%=ConstantParamAndAttribute.ID%>"
               value="<%=position.getId()%>"
               type="hidden"/>
        <input name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
               value="<%=position.getDepartmentId()%>"
               type="hidden"/>
        <div class="form-group">
            <label for="name">Наименование должности:</label>
            <input type="text"
                   id="name"
                   name="<%=ConstantParamAndAttribute.NAME%>"
                   value="<%=position.getName()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="education_level">Необходимый уровень образования:</label>
            <input type="text"
                   id="education_level"
                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                   value="<%=position.getEducationLevel()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="salary">Денежный оклад по должности:</label>
            <input type="text"
                   id="salary"
                   name="<%=ConstantParamAndAttribute.SALARY%>"
                   value="<%=position.getSalary()%>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%= ConstantAction.DEPARTMENT_INFO %>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=position.getDepartmentId()%>">
                Вернуться к просмотру информации об отделе
            </button>
        </div>
    </form>
</div>
</body>
</html>
