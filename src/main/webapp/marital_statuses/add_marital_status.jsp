<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("update_marital_status", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_marital_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_MARITAL_STATUS %>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_CURRENT%>"
                       value="true"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("marital_status", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.STATUS %>"
                                   placeholder=<%=LocalizationUtil.getMessage("marital_status_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("registration_date", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.REGISTRATION_DATE %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("document", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.DOCUMENT %>"
                                   placeholder=<%=LocalizationUtil.getMessage("document_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.MARITAL_STATUSES%>"
              method="get">
            <button class="footer-button, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("back_to_marital_status", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
