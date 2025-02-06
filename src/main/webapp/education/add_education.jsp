<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("add_education", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_education_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.ADD_EDUCATION%>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("education_level", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                                   placeholder=<%=LocalizationUtil.getMessage("education_level_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("institution", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.INSTITUTION%>"
                                   placeholder=<%=LocalizationUtil.getMessage("institution_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("faculty", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.FACULTY%>"
                                   placeholder=<%=LocalizationUtil.getMessage("faculty_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("specialization", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SPECIALIZATION%>"
                                   placeholder=<%=LocalizationUtil.getMessage("specialization_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("study_start", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_START%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("study_end", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%=ConstantParamAndAttribute.DATE_END%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.EDUCATION%>"
              method="get">
            <button class="footer-button, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                <%=LocalizationUtil.getMessage("back_to_education", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
