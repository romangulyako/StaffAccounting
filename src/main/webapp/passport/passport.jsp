<%@ page import="by.itacademy.jd2.dto.PassportDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Паспортные данные</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <div class="header-with-button">
            <div class="form-group">
                <h2>Паспортные данные сотрудника</h2>
            </div>
            <% PassportDTO passport = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT);
                if (passport != null) { %>
            <form action="<%=ConstantAction.UPDATE_PASSPORT%>" method="get">
                <button class="tab, button-edit"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=passport.getId()%>">
                    Изменить паспортные данные сотрудника
                </button>
            </form>
            <% } %>
        </div>
        <%if (passport == null) { %>
        <div class="form-group">
            <h3>Информация о паспорте отсутствует</h3>
            <form class="footer-buttons-block"
                  name="add_passport"
                  method="get"
                  action=<%=ConstantAction.ADD_PASSPORT%>>
                <button class="footer-button, button-add"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                    Добавить паспортные данные сотрудника
                </button>
            </form>
        </div>
        <% } else { %>
        <div class="information-block">
            <div class="information-item">
                <label>Серия:</label>
                <span><%=passport.getSeries()%></span>
            </div>
            <div class="information-item">
                <label>Номер:</label>
                <span><%=passport.getNumber()%></span>
            </div>
            <div class="information-item">
                <label>Идентификационный номер:</label>
                <span><%=passport.getIdentificationNumber()%></span>
            </div>
            <div class="information-item">
                <label>Адрес регистрации:</label>
                <span><%=passport.getRegistrationAddress().getCity() + ", ул. "
                        + passport.getRegistrationAddress().getStreet() + ", д. "
                        + passport.getRegistrationAddress().getHouse()%>
                <%if (passport.getRegistrationAddress().getApartment() != null) { %>
                <%=", кв. " + passport.getRegistrationAddress().getApartment()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label>Дата выдачи:</label>
                <span><%=passport.getDateIssue()%></span>
            </div>
            <div class="information-item">
                <label>Дата окончания действия:</label>
                <span><%=passport.getDateEndAction()%></span>
            </div>
            <div class="information-item">
                <label>Кем выдан:</label>
                <span><%=passport.getPublisher()%></span>
            </div>
        </div>
        <% } %>
        <div class="footer-buttons-block">
            <%if (passport != null) { %>
            <form action="<%=ConstantAction.DELETE_PASSPORT%>"
                  method="post">
                <button class="footer-button, button-delete"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=passport.getId()%>">
                    Удалить паспортные данные сотрудника
                </button>
            </form>
            <% } %>
            <form action="<%=ConstantAction.EMPLOYEE%>" method="get">
                <button class="footer-button"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                    Вернуться к просмотру сотрудника
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
