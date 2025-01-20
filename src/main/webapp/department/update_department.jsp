<%@ page import="by.itacademy.jd2.dto.DepartmentDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Изменить отдел</title>
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
        <h2>Заполните данные об отделе:</h2>
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
                        <legend>Наименование</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   value="<%=department.getName()%>"
                                   placeholder="Введите наименование отдела"
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Наименование в родительном падеже</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.GENITIVE_CASE_NAME %>"
                                   value="<%=department.getGenitiveCaseName()%>"
                                   placeholder="Введите наименование отдела в родительном падеже"
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Описание</legend>
                        <textarea rows="5"
                                  placeholder="Введите описание отдела"
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
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=department.getId()%>">
                Вернуться к просмотру отдела
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
