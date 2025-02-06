<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_position", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("enter_positions_data", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.ADD_POSITION%>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("item_name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NAME%>"
                                   placeholder=<%=LocalizationUtil.getMessage("position_name_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("necessary_education_level", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                                   placeholder=<%=LocalizationUtil.getMessage("necessary_education_level_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("salary", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SALARY%>"
                                   placeholder=<%=LocalizationUtil.getMessage("salary_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.DEPARTMENT_INFO%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                <%=LocalizationUtil.getMessage("back_to_department", locale)%>
            </button>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%= request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL) %>"/>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
