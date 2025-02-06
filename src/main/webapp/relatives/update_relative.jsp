<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("edit_relative", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("enter_relative_data", locale)%></h2>
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
                        <legend><%=LocalizationUtil.getMessage("surname", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                                   value="<%= relative.getPersonData().getSurname() %>"
                                   placeholder=<%=LocalizationUtil.getMessage("surname_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("name", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.NAME %>"
                                   value="<%= relative.getPersonData().getName() %>"
                                   placeholder=<%=LocalizationUtil.getMessage("name_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("patronymic", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                                    <%if (relative.getPersonData().getPatronymic() != null) { %>
                                   value="<%= relative.getPersonData().getPatronymic() %>"
                                    <% } %>
                                   placeholder=<%=LocalizationUtil.getMessage("patronymic_placeholder", locale)%>/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("birthday", locale)%></legend>
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
                        <legend><%=LocalizationUtil.getMessage("type_kinship", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                                   value="<%= relative.getTypeKinship()%>"
                                   placeholder=<%=LocalizationUtil.getMessage("type_kinship_placeholder", locale)%>
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
                <%=LocalizationUtil.getMessage("save_button", locale)%>
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=relative.getEmployeeId()%>">
                <%=LocalizationUtil.getMessage("back_to_relatives", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
