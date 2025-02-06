<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <%Locale localeError = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE);%>
    <title><%=LocalizationUtil.getMessage("error", localeError)%></title>
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
        <h1><%=LocalizationUtil.getMessage("error_arose", localeError)%></h1>
        <a href=<%= ConstantAction.LIST_EMPLOYEES %>> <%=LocalizationUtil.getMessage("back_to_employees", localeError)%> </a>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
