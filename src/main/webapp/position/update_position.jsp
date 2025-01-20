<%@ page import="by.itacademy.jd2.dto.PositionDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменить должность</title>
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
        <h2>Введите новые данные о должности:</h2>
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
                        <legend>Наименование</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NAME%>"
                                   value="<%=position.getName()%>"
                                   placeholder="Введите наименование должности"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Необходимый уровень образования</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.EDUCATION_LEVEL%>"
                                   value="<%=position.getEducationLevel()%>"
                                   placeholder="Введите необходимый уровень образования"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Денежный оклад</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SALARY%>"
                                   value="<%=position.getSalary()%>"
                                   placeholder="Введите денежный оклад"
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
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=position.getDepartmentId()%>">
                Вернуться к просмотру информации об отделе
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
