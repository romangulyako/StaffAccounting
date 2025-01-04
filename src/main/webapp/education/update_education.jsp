<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить образование</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<% EducationDTO education = (EducationDTO) request.getAttribute(ConstantParamAndAttribute.EDUCATION); %>
<div class="form-container">
    <div class="form-group">
        <h2>Введите новые данные об образовании:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.UPDATE_EDUCATION %>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= education.getId() %>"
               required/>
        <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
               type="hidden"
               value="<%= education.getEmployeeId() %>"
               required/>
        <div class="form-group">
            <label for="educationLevel">Уровень образования:</label>
            <input type="text"
                   id="educationLevel"
                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                   value="<%=education.getEducationLevel()%>"/>
        </div>
        <div class="form-group">
            <label for="institution">Учебное заведение:</label>
            <input type="text"
                   id="institution"
                   name="<%=ConstantParamAndAttribute.INSTITUTION%>"
                   value="<%=education.getInstitution()%>"/>
        </div>
        <div class="form-group">
            <label for="faculty">Факультет:</label>
            <input type="text"
                   id="faculty"
                   name="<%=ConstantParamAndAttribute.FACULTY%>"
                   value="<%=education.getFaculty()%>"/>
        </div>
        <div class="form-group">
            <label for="specialization">Специализация:</label>
            <input type="text"
                   id="specialization"
                   name="<%=ConstantParamAndAttribute.SPECIALIZATION%>"
                   value="<%=education.getSpecialization()%>"/>
        </div>
        <div class="form-group">
            <label for="dateStart">Дата начала обучения:</label>
            <input type="date"
                   id="dateStart"
                   name="<%=ConstantParamAndAttribute.DATE_START%>"
                   value="<%=education.getDateStart()%>"/>
        </div>
        <div class="form-group">
            <label for="dateEnd">Дата окончания обучения:</label>
            <input type="date"
                   id="dateEnd"
                   name="<%=ConstantParamAndAttribute.DATE_END%>"
                   value="<%=education.getDateEnd()%>"/>
        </div>
    </form>
    <form class="tabs" action="<%= ConstantAction.EDUCATION %>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=education.getEmployeeId()%>">
                Вернуться к просмотру образования
            </button>
        </div>
    </form>
</div>
</body>
</html>
