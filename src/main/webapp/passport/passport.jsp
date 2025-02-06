<%@ page import="by.itacademy.jd2.dto.PassportDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("passport", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <% PassportDTO passport = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT);%>
        <div <%if (passport != null) { %>
                class="header-with-button"
        <% } %>>
            <div class="form-group">
                <h2><%=LocalizationUtil.getMessage("employees_passport", locale)%></h2>
            </div>
            <% if (passport != null) { %>
            <form action="<%=ConstantAction.UPDATE_PASSPORT%>" method="get">
                <button class="tab, button-edit"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=passport.getId()%>">
                    <%=LocalizationUtil.getMessage("edit_button", locale)%>
                </button>
            </form>
            <% } %>
        </div>
        <%if (passport == null) { %>
        <div class="form-group">
            <h3><%=LocalizationUtil.getMessage("passport_absent", locale)%></h3>
            <form class="footer-buttons-block"
                  name="add_passport"
                  method="get"
                  action=<%=ConstantAction.ADD_PASSPORT%>>
                <button class="footer-button, button-add"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                    <%=LocalizationUtil.getMessage("add_passport", locale)%>
                </button>
            </form>
        </div>
        <% } else { %>
        <div class="information-block">
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("series", locale)%></label>
                <span><%=passport.getSeries()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("number", locale)%></label>
                <span><%=passport.getNumber()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("identification", locale)%></label>
                <span><%=passport.getIdentificationNumber()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("registration_address", locale)%></label>
                <span><%=passport.getRegistrationAddress().getCity() + ", " + LocalizationUtil.getMessage("street", locale) + " "
                        + passport.getRegistrationAddress().getStreet() + ", " + LocalizationUtil.getMessage("house", locale) + " "
                        + passport.getRegistrationAddress().getHouse()%>
                <%if (passport.getRegistrationAddress().getApartment() != null) { %>
                <%=", " + LocalizationUtil.getMessage("apartment", locale) + " " + passport.getRegistrationAddress().getApartment()%>
                <% } %>
            </span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("date_issue", locale)%></label>
                <span><%=passport.getDateIssue()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("date_end_action", locale)%></label>
                <span><%=passport.getDateEndAction()%></span>
            </div>
            <div class="information-item">
                <label><%=LocalizationUtil.getMessage("publisher", locale)%></label>
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
                    <%=LocalizationUtil.getMessage("delete_button", locale)%>
                </button>
            </form>
            <% } %>
            <form action="<%=ConstantAction.EMPLOYEE%>" method="get">
                <button class="footer-button"
                        name="<%=ConstantParamAndAttribute.ID%>"
                        value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                    <%=LocalizationUtil.getMessage("back_to_employee", locale)%>
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
