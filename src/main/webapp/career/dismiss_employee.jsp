<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Увольнение сотрудника</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Заполните форму для увольнения сотрудника:</h2>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.DISMISS%>"
                  method="post">
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Сотрудник</legend>
                        <select name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                                id="employee"
                                required>
                            <option value=""
                                    disabled
                                    selected>
                                Выберите сотрудника
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
                        <legend>Дата увольнения</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.DATE_DISMISS %>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Приказ</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.ORDER_DISMISS %>"
                                   placeholder="Введите реквизиты приказа"
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
                Уволить
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
