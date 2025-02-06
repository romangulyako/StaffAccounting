<%@ page import="by.itacademy.jd2.dto.MaritalStatusDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("edit_marital_status", locale)%></title>
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
        <% MaritalStatusDTO maritalStatus = (MaritalStatusDTO) request.getAttribute(ConstantParamAndAttribute.MARITAL_STATUS); %>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.UPDATE_MARITAL_STATUS %>"
                  method="post">
                <input name="<%= ConstantParamAndAttribute.ID %>"
                       type="hidden"
                       value="<%= maritalStatus.getId() %>"
                       required/>
                <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                       type="hidden"
                       value="<%= maritalStatus.getEmployeeId() %>"
                       required/>
                <input name="<%= ConstantParamAndAttribute.IS_CURRENT %>"
                       type="hidden"
                       value="<%= maritalStatus.isCurrent() %>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("marital_status", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.STATUS %>"
                                   value="<%= maritalStatus.getStatus() %>"
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
                                   value="<%= maritalStatus.getRegistrationDate() %>"
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
                                   value="<%= maritalStatus.getDocument() %>"
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
            <button class="footer-button, button-add"
                    form="save">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%= maritalStatus.getEmployeeId() %>">
                <%=LocalizationUtil.getMessage("back_to_marital_status", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
