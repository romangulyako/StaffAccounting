<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Ошибка</title>
    <style>
        <%@include file="/resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="error-container">
        <div class="error-icon">
            &#9888;
        </div>
        <h1>Возникла какая-то ошибка!</h1>
        <a href=<%= ConstantAction.LIST_EMPLOYEES %>> Вернуться к списку сотрудников </a>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
