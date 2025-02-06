<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_department_title", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_department_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_DEPARTMENT %>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("item_name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   placeholder=<%=LocalizationUtil.getMessage("department_name_placeholder", locale)%>
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("department_genitive_name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.GENITIVE_CASE_NAME %>"
                                   placeholder=<%=LocalizationUtil.getMessage("department_genitive_nam_placeholder", locale)%>
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("description", locale)%></legend>
                        <textarea rows="5"
                                  placeholder=<%=LocalizationUtil.getMessage("description_placeholder", locale)%>
                                  name="<%=ConstantParamAndAttribute.DESCRIPTION%>"></textarea>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.LIST_DEPARTMENTS%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save"
                    type="submit">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
            value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>">
                <%=LocalizationUtil.getMessage("back_to_departments_list", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
