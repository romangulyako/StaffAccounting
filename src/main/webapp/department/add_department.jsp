<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавить отдел</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Заполните данные об отделе:</h2>
        <div class="filling-form">
            <form id="save"
                  action="<%= ConstantAction.ADD_DEPARTMENT %>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                       value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>"/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Наименование</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   placeholder="Введите название отдела"
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
                                   placeholder="Введите название отдела в родительном падеже"
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Описание</legend>
                        <textarea rows="5"
                                  placeholder="Введите описание отдела"
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
                Сохранить
            </button>
            <button class="footer-button"
            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
            value="<%=request.getAttribute(ConstantParamAndAttribute.IS_ACTUAL)%>">
                Вернуться к списку отделов
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
