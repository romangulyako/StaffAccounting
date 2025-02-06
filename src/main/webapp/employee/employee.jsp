<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.entity.embedded.PersonData" %>
<%@ page import="by.itacademy.jd2.entity.embedded.Address" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<% EmployeeDTO employee = (EmployeeDTO) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE);
    PersonData personData = employee.getPersonData();
    Address homeAddress = employee.getHomeAddress();
    Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE);
%>
<head>
    <title><%=personData.getSurname() + " "
            + personData.getName() + " " %>
        <%if (personData.getPatronymic() != null) {%>
        <%=personData.getPatronymic()%>
        <%}%>
    </title>
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
            <h2><%=LocalizationUtil.getMessage("employees_information", locale)%></h2>
            <form name="update_employee"
                  method="get"
                  action="<%= ConstantAction.UPDATE_EMPLOYEE %>">
                <button class="button-edit"
                        name="<%= ConstantParamAndAttribute.ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("edit_button", locale)%>
                </button>
            </form>
        </div>
        <div class="information-block">
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("surname", locale)%></label>
                <span><%=personData.getSurname()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("name", locale)%></label>
                <span><%=personData.getName()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("patronymic", locale)%></label>
                <span>
                <%if (personData.getPatronymic() != null) { %>
                <%=personData.getPatronymic()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("birthday", locale)%></label>
                <span><%=personData.getBirthday()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("home_address", locale)%></label>
                <span>
                <%=homeAddress.getCity()%>
                , ул. <%=homeAddress.getStreet()%>
                , д. <%=homeAddress.getHouse()%>
                <%if (homeAddress.getApartment() != null) { %>
                , кв. <%=homeAddress.getApartment()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("phone", locale)%></label>
                <span>
                <%if (employee.getPhone() != null) { %>
                <%=employee.getPhone()%>
                <% } %>
            </span>
            </div>
        </div>
        <div class="footer-buttons-block">
            <form action="<%=ConstantAction.PASSPORT%>" method="get">
                <button class="footer-button" name="<%= ConstantParamAndAttribute.ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("passport", locale)%>
                </button>
            </form>
            <form action="<%=ConstantAction.RELATIVES%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("relatives", locale)%>
                </button>
            </form>
            <form action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("marital_status", locale)%>
                </button>
            </form>
            <form action="<%=ConstantAction.EDUCATION%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("education", locale)%>
                </button>
            </form>
            <form action="<%=ConstantAction.CAREER%>" method="get">
                <button class="footer-button"
                        name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                        value="<%= employee.getId() %>">
                    <%=LocalizationUtil.getMessage("career", locale)%>
                </button>
            </form>
        </div>
        <div class="footer-buttons-block">
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>" method="get">
                <button class="footer-button"><%=LocalizationUtil.getMessage("back_to_employees", locale)%></button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
