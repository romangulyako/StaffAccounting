<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="by.itacademy.jd2.utils.LocalizationUtil" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <%Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("dismiss_title", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_dismiss_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.DISMISS%>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("employee_legend", locale)%></legend>
                        <select name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                                id="employee"
                                required>
                            <option value=""
                                    disabled
                                    selected>
                                <%=LocalizationUtil.getMessage("select_employee", locale)%>
                            </option>
                            <% List<EmployeeItemDTO> employees = (List<EmployeeItemDTO>) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ITEMS);
                                if (employees != null) {
                                    for (EmployeeItemDTO employee : employees) {
                            %>
                            <option value="<%=employee.getId()%>">
                                <%=employee.getSurname() + " " + employee.getName() + " " + employee.getPatronymic()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("dismiss_date", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.DATE_DISMISS %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("order", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.ORDER_DISMISS %>"
                                   placeholder=<%=LocalizationUtil.getMessage("order_placeholder", locale)%>
                                   required/>
                        </label>
                    </fieldset>
                </div>
            </form>
        </div>
        <form class="footer-buttons-block">
            <button class="footer-button, button-add"
                    form="save"
                    type="submit">
                <%=LocalizationUtil.getMessage("dismiss_button", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
