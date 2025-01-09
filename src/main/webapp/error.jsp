<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Ошибка</title>
    <style>
        <%@include file="/resources/css/styles.css"%>
    </style>
</head>
<body class="body-error">
<div class="error-container">
    <div class="error-icon">
        &#9888;
    </div>
    <h1>Возникла какая-то ошибка!</h1>
    <a href=<%= ConstantAction.LIST_EMPLOYEES %>> Вернуться к списку сотрудников </a>
</div>
</body>
</html>
