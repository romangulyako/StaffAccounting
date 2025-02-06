<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("edit_position", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <% PositionDTO position = (PositionDTO) request.getAttribute(ConstantParamAndAttribute.POSITION);%>
        <h2><%=LocalizationUtil.getMessage("enter_positions_data", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.UPDATE_POSITION%>"
                  method="post">
                <input name="<%=ConstantParamAndAttribute.ID%>"
                       value="<%=position.getId()%>"
                       type="hidden"/>
                <input name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                       value="<%=position.getDepartmentId()%>"
                       type="hidden"/>
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("item_menu", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NAME%>"
                                   value="<%=position.getName()%>"
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
                                   value="<%=position.getEducationLevel()%>"
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
                                   value="<%=position.getSalary()%>"
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
                    form="save">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=position.getDepartmentId()%>">
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
