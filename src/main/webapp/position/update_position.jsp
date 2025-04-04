<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <input type="hidden"
               name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
               value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
        <div class="form-group">
            <label for="name">Наименование должности:</label>
            <input type="text"
                   id="name"
                   name="<%=ConstantParamAndAttribute.NAME%>"
                   value="<%=position.getName()%>"
                   placeholder="Введите наименование должности"
                   required/>
        </div>
        <div class="form-group">
            <label for="education_level">Необходимый уровень образования:</label>
            <input type="text"
                   id="education_level"
                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                   value="<%=position.getEducationLevel()%>"
                   placeholder="Введите необходимый уровень образования"
                   required/>
        </div>
        <div class="form-group">
            <label for="salary">Денежный оклад по должности:</label>
            <input type="text"
                   id="salary"
                   name="<%=ConstantParamAndAttribute.SALARY%>"
                   value="<%=position.getSalary()%>"
                   placeholder="Введите денежный оклад"
                   required/>
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
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%= request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL) %>"/>
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
