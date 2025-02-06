<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_employee", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_employee_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_EMPLOYEE %>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("surname", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                                   placeholder=<%=LocalizationUtil.getMessage("surname_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   placeholder=<%=LocalizationUtil.getMessage("name_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("patronymic", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                                   placeholder=<%=LocalizationUtil.getMessage("patronymic_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("birthday", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("living_city", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_CITY %>"
                                   placeholder=<%=LocalizationUtil.getMessage("living_city_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("living_street", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_STREET %>"
                                   placeholder=<%=LocalizationUtil.getMessage("living_street_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("living_house", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_HOUSE %>"
                                   placeholder=<%=LocalizationUtil.getMessage("living_house_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("living_apartment", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.RESIDENCE_APARTMENT %>"
                                   placeholder=<%=LocalizationUtil.getMessage("living_apartment_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("phone", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PHONE %>"
                                   placeholder=<%=LocalizationUtil.getMessage("phone_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.LIST_EMPLOYEES%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save"
                    type="submit">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button">
                <%=LocalizationUtil.getMessage("back_to_employees", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
