<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавить должность</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Введите данные о должности:</h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.ADD_POSITION%>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Наименование</legend>
                        <label>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NAME%>"
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
                    form="save"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ID)%>">
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
