<%@ page import="by.itacademy.jd2.dto.EducationDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменить образование</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Введите новые данные об образовании:</h2>
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
                        <legend>Уровень образования</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                                   value="<%=education.getEducationLevel()%>"
                                   placeholder="Введите уровень образования"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Учебное заведение</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.INSTITUTION%>"
                                   value="<%=education.getInstitution()%>"
                                   placeholder="Введите название учебного заведения"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Факультет</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.FACULTY%>"
                                    <%if (education.getFaculty() != null) { %>
                                   value="<%=education.getFaculty()%>"
                                    <% } %>
                                   placeholder="Введите название факультета"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Специализация</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SPECIALIZATION%>"
                                    <%if (education.getSpecialization() != null) { %>
                                   value="<%=education.getSpecialization()%>"
                                    <% } %>
                                   placeholder="Введите специализацию"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата начала обучения</legend>
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
                        <legend>Дата окончания обучения</legend>
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
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=education.getEmployeeId()%>">
                Вернуться к просмотру образования
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
