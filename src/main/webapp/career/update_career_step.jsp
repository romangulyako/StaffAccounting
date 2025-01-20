<%@ page import="by.itacademy.jd2.dto.CareerStepGetDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.PositionItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Изменить послужной список</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div class="form-group">
        <h2>Заполните обновленную информацию:</h2>
        <% CareerStepGetDTO careerStep = (CareerStepGetDTO) request.getAttribute(ConstantParamAndAttribute.CAREER_STEP); %>
        <div class="filling-form">
            <form id="save"
                  action="<%=ConstantAction.UPDATE_CAREER_STEP%>"
                  method="post">
                <input type="hidden"
                       name="<%=ConstantParamAndAttribute.CAREER_ID%>"
                       value="<%=careerStep.getId()%>"
                       required/>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Должность</legend>
                        <select name="<%=ConstantParamAndAttribute.NEW_POSITION_ID%>"
                                required>
                            <option value="<%=careerStep.getPositionId()%>"
                                    selected>
                                <%=careerStep.getPositionName() + " " + careerStep.getDepartmentGenitiveCaseName()%>
                            </option>
                            <% List<PositionItemDTO> positions = (List<PositionItemDTO>) request.getAttribute(ConstantParamAndAttribute.POSITION_ITEMS);
                                if (positions != null) {
                                    for (PositionItemDTO position : positions) {
                            %>
                            <option value="<%=position.getId()%>">
                                <%=position.getPositionName() + " " + position.getDepartmentGenitiveCaseName()%>
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
                        <legend>Дата назначения</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT %>"
                                   value="<%=careerStep.getDateOfAppointment()%>"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Приказ</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.ORDER_APPOINTMENT %>"
                                   value="<%=careerStep.getOrderAppointment()%>"
                                   placeholder="Введите реквизиты приказа о назначении"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <%if (careerStep.getDateOfLiberation() != null) { %>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Дата освобождения должности</legend>
                        <label>
                            <input type="date"
                                   name="<%= ConstantParamAndAttribute.DATE_OF_LIBERATION %>"
                                   value="<%=careerStep.getDateOfLiberation()%>"
                                   required>
                        </label>
                    </fieldset>
                </div>
                <div class="input-wrapper">
                    <fieldset>
                        <legend>Приказ</legend>
                        <label>
                            <input type="text"
                                   name="<%= ConstantParamAndAttribute.ORDER_LIBERATION %>"
                                   value="<%=careerStep.getOrderLiberation()%>"
                                   placeholder="Введите реквизиты приказа об освобождении должности"
                                   required/>
                        </label>
                    </fieldset>
                </div>
                <% } %>
            </form>
        </div>
        <form class="footer-buttons-block"
              action="<%=ConstantAction.CAREER%>"
              method="get">
            <button class="footer-button, button-add"
                    form="save"
                    type="submit"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=careerStep.getEmployeeId()%>">
                Сохранить
            </button>
            <button class="footer-button"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=careerStep.getEmployeeId()%>">
                Вернуться к просмотру карьеры сотрудника
            </button>
        </form>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
