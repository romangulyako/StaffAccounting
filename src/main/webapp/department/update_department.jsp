<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<% DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT); %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Введите информацию об отделе:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= department.getId() %>"
               required>
        <div class="form-group">
            <label for="name">Наименование:</label>
            <input type="text"
                   id="name"
                   name="<%= ConstantParamAndAttribute.NAME %>"
                   value="<%=department.getName()%>"
                   placeholder="Введите название отдела"
                   required>
        </div>
        <div class="form-group">
            <label for="genitive_case_name">Наименование в родительном падеже:</label>
            <input type="text"
                   id="genitive_case_name"
                   name="<%= ConstantParamAndAttribute.GENITIVE_CASE_NAME %>"
                   value="<%=department.getGenitiveCaseName()%>"
                   placeholder="Введите название отдела в родительном падеже"
                   required>
        </div>
        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea id="description"
                      rows="5"
                      placeholder="Введите описание отдела"
                      name="<%=ConstantParamAndAttribute.DESCRIPTION%>">
                <%=department.getDescription()%>
            </textarea>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.LIST_DEPARTMENTS%>" method="get">
        <div class="form-group">
            <button class="tab, button-add"
                    form="save"
                    type="submit">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab">
                Вернуться к списку отделов
            </button>
        </div>
    </form>
</div>
</body>
</html>
