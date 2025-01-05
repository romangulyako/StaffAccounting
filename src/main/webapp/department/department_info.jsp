<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT); %>
<head>
    <title><%=department.getName()%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container, general-div">
    <div class="item-header">
        <h2>Информация об отделе:</h2>
        <form name="update_department"
              action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
              method="get">
            <button class="button-edit"
                    name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= department.getId() %>">
                Изменить
            </button>
        </form>
    </div>
    <div class="item-info">
        <div class="item-info-item">
            <label>Наименование:</label>
            <span><%=department.getName()%></span>
        </div>
        <div class="item-info-item">
            <label>Наименование в родительном падеже:</label>
            <span><%=department.getGenitiveCaseName()%></span>
        </div>
        <div class="item-info-item">
            <label>Описание:</label>
            <span><%=department.getDescription()%></span>
        </div>
    </div>
    <div class="tabs">
        <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
              method="get">
            <button class="tab">Вернуться к списку отделов</button>
        </form>
    </div>
</div>
</body>
</html>
