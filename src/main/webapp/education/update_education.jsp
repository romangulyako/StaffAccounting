<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("edit_education", locale)%></title>
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
        <% EducationDTO education = (EducationDTO) request.getAttribute(ConstantParamAndAttribute.EDUCATION); %>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.UPDATE_EDUCATION %>"
                  method="post">
                <input name="<%= ConstantParamAndAttribute.ID %>"
                       type="hidden"
                       value="<%= education.getId() %>"
                       required/>
                <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                       type="hidden"
                       value="<%= education.getEmployeeId() %>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("education_level", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                                   value="<%=education.getEducationLevel()%>"
                                   placeholder=<%=LocalizationUtil.getMessage("education_level_placeholder", locale)%><
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
                                   value="<%=education.getInstitution()%>"
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
                                    <%if (education.getFaculty() != null) { %>
                                   value="<%=education.getFaculty()%>"
                                    <% } %>
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
                                    <%if (education.getSpecialization() != null) { %>
                                   value="<%=education.getSpecialization()%>"
                                    <% } %>
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
                                   value="<%=education.getDateStart()%>"
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
                                   value="<%=education.getDateEnd()%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%= ConstantAction.EDUCATION %>"
              method="get">
            <button class="footer-button, button-add"
                    form="save">
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=education.getEmployeeId()%>">
                <%=LocalizationUtil.getMessage("back_to_education", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
