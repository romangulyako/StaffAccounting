<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменить родственника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Введите данные о родственнике:</h2>
        <div class="filling-form">
            <% RelativeDTO relative = (RelativeDTO) request.getAttribute(ConstantParamAndAttribute.RELATIVE); %>
            <form id="save"
                  action="<%=ConstantAction.UPDATE_RELATIVE%>"
                  method="post">
                <input name="<%= ConstantParamAndAttribute.ID %>"
                       type="hidden"
                       value="<%= relative.getId() %>"
                       required/>
                <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                       type="hidden"
                       value="<%= relative.getEmployeeId() %>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Фамилия</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                                   value="<%= relative.getPersonData().getSurname() %>"
                                   placeholder="Введите фамилию"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Имя</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   value="<%= relative.getPersonData().getName() %>"
                                   placeholder="Введите имя"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Отчество</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                                    <%if (relative.getPersonData().getPatronymic() != null) { %>
                                   value="<%= relative.getPersonData().getPatronymic() %>"
                                    <% } %>
                                   placeholder="Введите отчество"/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата рождения</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                                   value="<%= relative.getPersonData().getBirthday() %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Тип родства</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                                   value="<%= relative.getTypeKinship()%>"
                                   placeholder="Введите тип родства"
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.RELATIVES%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save">
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=relative.getEmployeeId()%>">
                Вернуться к просмотру родственников
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
