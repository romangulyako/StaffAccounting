<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeItemDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.PositionItemDTO" %>
<%@ page import="java.util.Locale" %>
<%@ page import="by.itacademy.jd2.utils.LocalizationUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <% Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <title><%=LocalizationUtil.getMessage("appointment_title", locale)%></title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2><%=LocalizationUtil.getMessage("filling_appointment_form_message", locale)%></h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.APPOINTMENT%>"
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
                        <legend><%=LocalizationUtil.getMessage("position", locale)%></legend>
                        <select name="<%=ConstantParamAndAttribute.NEW_POSITION_ID%>"
                                id="position"
                                required>
                            <option value=""
                                    disabled
                                    selected>
                                <%=LocalizationUtil.getMessage("select_position", locale)%>
                            </option>
                            <% List<PositionItemDTO> positions = (List<PositionItemDTO>) request.getAttribute(ConstantParamAndAttribute.POSITION_ITEMS);
                                if (positions != null) {
                                    for (PositionItemDTO position : positions) {
                            %>
                            <option value="<%=position.getId()%>">
                                <%=position.getPositionName() + " " + position.getDepartmentGenitiveCaseName() %>
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
                        <legend><%=LocalizationUtil.getMessage("appointment_date_legend", locale)%></legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend><%=LocalizationUtil.getMessage("appointment_order", locale)%></legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.ORDER_APPOINTMENT %>"
                                   placeholder="<%=LocalizationUtil.getMessage("order_placeholder", locale)%>"
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
                <%=LocalizationUtil.getMessage("appoint_button", locale)%>
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
