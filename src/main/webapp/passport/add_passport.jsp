<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_passport", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("enter_passport_data", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.ADD_PASSPORT%>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("series", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SERIES%>"
                                   minlength="2"
                                   maxlength="2"
                                   placeholder=<%=LocalizationUtil.getMessage("series_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("number", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NUMBER%>"
                                   placeholder=<%=LocalizationUtil.getMessage("number_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("identification", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.IDENTIFICATION_NUMBER%>"
                                   maxlength="14"
                                   minlength="14"
                                   placeholder=<%=LocalizationUtil.getMessage("identification_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("register_city", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_CITY%>"
                                   placeholder=<%=LocalizationUtil.getMessage("register_city_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("register_street", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_STREET%>"
                                   placeholder=<%=LocalizationUtil.getMessage("register_street_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("register_house", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_HOUSE%>"
                                   placeholder=<%=LocalizationUtil.getMessage("register_house_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("register_apartment", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.REGISTRATION_APARTMENT%>"
                                   placeholder=<%=LocalizationUtil.getMessage("register_apartment_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("date_issue", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_ISSUE%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("date_end_action", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_END_ACTION%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("publisher", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.PUBLISHER%>"
                                   placeholder=<%=LocalizationUtil.getMessage("publisher_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.PASSPORT%>"
              method="get">
            <button class="footer-button, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                <%=LocalizationUtil.getMessage("back_to_passport", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
