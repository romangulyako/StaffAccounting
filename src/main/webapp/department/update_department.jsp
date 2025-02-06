<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("edit_department", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <% DepartmentDTO department = (DepartmentDTO) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT); %>
        <h2><%=LocalizationUtil.getMessage("filling_department_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.UPDATE_DEPARTMENT%>"
                  method="post">
                <input type="hidden"
                       name="<%= ConstantParamAndAttribute.ID %>"
                       value="<%= department.getId() %>"
                       required>
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("item_name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   value="<%=department.getName()%>"
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
                                   value="<%=department.getGenitiveCaseName()%>"
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
                                  name="<%=ConstantParamAndAttribute.DESCRIPTION%>">
                            <%if (department.getDescription() != null) { %>
                            <%=department.getDescription()%>
                            <% } %>
                        </textarea>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.DEPARTMENT_INFO%>"
              method="get">
            <input type="hidden"
            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                   value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
            <button class="footer-button, button-add"
                    form="save"
                    type="submit"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=department.getId()%>">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=department.getId()%>">
                <%=LocalizationUtil.getMessage("back_to_department", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
