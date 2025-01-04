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
<%@include file="../menu.jsp" %>
<div class="container, general-div">
    <% PassportDTO passportDTO = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT);
        if (passportDTO == null) { %>
    <div class="item-header">
        <h2>Информация о паспорте отсутствует</h2>
    </div>
    <div class="tabs">
        <form name="add_passport"
              method="get"
              action=<%=ConstantAction.ADD_PASSPORT%>>
            <button class="button-add"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Добавить паспортные данные сотрудника
            </button>
        </form>
    </div>
    <% } else { %>
    <div class="item-info">
        <div class="item-info-item">
            <label>Серия:</label>
            <span><%=passportDTO.getSeries()%></span>
        </div>
        <div class="item-info-item">
            <label>Номер:</label>
            <span><%=passportDTO.getNumber()%></span>
        </div>
        <div class="item-info-item">
            <label>Идентификационный номер:</label>
            <span><%=passportDTO.getIdentificationNumber()%></span>
        </div>
        <div class="item-info-item">
            <label>Адрес регистрации:</label>
            <span><%=passportDTO.getRegistrationAddress().getCity() + ", ул. "
                    + passportDTO.getRegistrationAddress().getStreet() + ", д. "
                    + passportDTO.getRegistrationAddress().getHouse() + ", кв. "
                    + passportDTO.getRegistrationAddress().getApartment()%>
            </span>
        </div>
        <div class="item-info-item">
            <label>Дата выдачи:</label>
            <span><%=passportDTO.getDateIssue()%></span>
        </div>
        <div class="item-info-item">
            <label>Дата окончания действия:</label>
            <span><%=passportDTO.getDateEndAction()%></span>
        </div>
        <div class="item-info-item">
            <label>Кем выдан:</label>
            <span><%=passportDTO.getPublisher()%></span>
        </div>
    </div>
    <div class="tabs">
        <form action="<%=ConstantAction.UPDATE_PASSPORT%>" method="get">
            <button class="tab, button-edit"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passportDTO.getId()%>">
                Изменить паспортные данные сотрудника
            </button>
        </form>
        <form action="<%=ConstantAction.DELETE_PASSPORT%>" method="post">
            <button class="tab, button-delete" name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passportDTO.getId()%>">
                Удалить паспортные данные сотрудника
            </button>
        </form>
    </div>
    <% } %>
    <div class="tabs">
        <form action="<%=ConstantAction.EMPLOYEE%>" method="get">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Вернуться к просмотру сотрудника
            </button>
        </form>
    </div>
</div>
</body>
</html>
