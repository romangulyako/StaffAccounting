<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_relative", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("enter_relative_data", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.ADD_RELATIVE%>"
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
                        <legend><%=LocalizationUtil.getMessage("type_kinship", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                                   placeholder=<%=LocalizationUtil.getMessage("type_kinship_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block" action="<%=ConstantAction.RELATIVES%>" method="get">
            <button class="footer-button, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("back_to_relatives", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
