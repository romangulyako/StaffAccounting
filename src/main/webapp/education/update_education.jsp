<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменить образование</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<% EducationDTO education = (EducationDTO) request.getAttribute(ConstantParamAndAttribute.EDUCATION); %>
<div class="form-container, general-div">
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
                   value="<%=education.getEducationLevel()%>"
                   placeholder="Введите уровень образования"
                   required/>
        </div>
        <div class="form-group">
            <label for="institution">Учебное заведение:</label>
            <input type="text"
                   id="institution"
                   name="<%=ConstantParamAndAttribute.INSTITUTION%>"
                   value="<%=education.getInstitution()%>"
                   placeholder="Введите название учебного заведения"
                   required/>
        </div>
        <div class="form-group">
            <label for="faculty">Факультет:</label>
            <input type="text"
                   id="faculty"
                   name="<%=ConstantParamAndAttribute.FACULTY%>"
                   <%if (education.getFaculty() != null) { %>
                   value="<%=education.getFaculty()%>"
                   <%}%>
                   placeholder="Введите название факультета"/>
        </div>
        <div class="form-group">
            <label for="specialization">Специализация:</label>
            <input type="text"
                   id="specialization"
                   name="<%=ConstantParamAndAttribute.SPECIALIZATION%>"
                    <%if (education.getSpecialization() != null) { %>
                   value="<%=education.getSpecialization()%>"
                    <%}%>
                   placeholder="Введите специализацию"/>
        </div>
        <div class="form-group">
            <label for="dateStart">Дата начала обучения:</label>
            <input type="date"
                   id="dateStart"
                   name="<%=ConstantParamAndAttribute.DATE_START%>"
                   value="<%=education.getDateStart()%>"
                   required/>
        </div>
        <div class="form-group">
            <label for="dateEnd">Дата окончания обучения:</label>
            <input type="date"
                   id="dateEnd"
                   name="<%=ConstantParamAndAttribute.DATE_END%>"
                   value="<%=education.getDateEnd()%>"
                   required/>
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
