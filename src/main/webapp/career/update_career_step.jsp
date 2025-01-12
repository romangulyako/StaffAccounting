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
<%@include file="../menu.jsp" %>
<% CareerStepGetDTO careerStep = (CareerStepGetDTO) request.getAttribute(ConstantParamAndAttribute.CAREER_STEP); %>
<div class="form-container, general-div">
    <div class="form-group">
        <h2>Заполните обновленную информацию:</h2>
    </div>
    <form id="save"
          action="<%=ConstantAction.UPDATE_CAREER_STEP%>"
          method="post">
        <input type="hidden"
               name="<%=ConstantParamAndAttribute.CAREER_ID%>"
               value="<%=careerStep.getId()%>"
               required>
        <div class="form-group">
            <label for="position">Выберите должность</label>
            <select name="<%=ConstantParamAndAttribute.NEW_POSITION_ID%>"
                    id="position"
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
        </div>
        <div class="form-group">
            <label for="date_of_appointment">Дата назначения:</label>
            <input type="date"
                   id="date_of_appointment"
                   name="<%= ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT %>"
                   value="<%=careerStep.getDateOfAppointment()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="order">Приказ о назначении:</label>
            <input type="text"
                   id="order"
                   name="<%= ConstantParamAndAttribute.ORDER_APPOINTMENT %>"
                   value="<%=careerStep.getOrderAppointment()%>"
                   required>
        </div>
        <%if(careerStep.getDateOfLiberation() != null) {%>
        <div class="form-group">
            <label for="date_of_liberation">Дата освобождения должности:</label>
            <input type="date"
                   id="date_of_liberation"
                   name="<%= ConstantParamAndAttribute.DATE_OF_LIBERATION %>"
                   value="<%=careerStep.getDateOfLiberation()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="orderLiberation">Приказ об освобождении должности:</label>
            <input type="text"
                   id="orderLiberation"
                   name="<%= ConstantParamAndAttribute.ORDER_LIBERATION %>"
                   value="<%=careerStep.getOrderLiberation()%>"
                   required>
        </div>
        <% } %>
    </form>
    <form class="tabs"
          action="<%=ConstantAction.CAREER%>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add"
                    form="save"
                    type="submit"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=careerStep.getEmployeeId()%>">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=careerStep.getEmployeeId()%>">
                Вернуться к просмотру карьеры сотрудника
            </button>
        </div>
    </form>
</div>
</body>
</html>
