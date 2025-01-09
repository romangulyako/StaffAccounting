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
               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
               value="<%=careerStep.getId().getEmployee()%>"
               required>
        <input type="hidden"
               name="<%=ConstantParamAndAttribute.POSITION_ID%>"
               value="<%=careerStep.getId().getPosition()%>"
               required>
        <input type="hidden"
               name="<%=ConstantParamAndAttribute.DATE_OF_APPOINTMENT%>"
               value="<%=careerStep.getId().getDateOfAppointment()%>"
               required>
        <div class="form-group">

            <label for="position">Выберите должность</label>
            <select name="<%=ConstantParamAndAttribute.NEW_POSITION_ID%>"
                    id="position"
                    required>
                <option value=""
                        disabled
                        selected>
                    Выберите должность
                </option>
                <% List<PositionItemDTO> positions = (List<PositionItemDTO>) request.getAttribute(ConstantParamAndAttribute.POSITION_ITEMS);
                    if (positions != null) {
                        for (PositionItemDTO position : positions) {
                %>
                <option value="<%=position.getId()%>">
                    <%=position.getFullName()%>
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
                   value="<%=careerStep.getId().getDateOfAppointment()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="order">Приказ о назначении:</label>
            <input type="text"
                   id="order"
                   name="<%= ConstantParamAndAttribute.ORDER %>"
                   value="<%=careerStep.getOrder()%>"
                   required>
        </div>
    </form>
    <form class="tabs"
          action="<%=ConstantAction.CAREER%>"
          method="get">
        <div class="form-group">
            <button class="tab, button-add"
                    form="save"
                    type="submit">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=careerStep.getId().getEmployee()%>">
                Вернуться к просмотру карьеры сотрудника
            </button>
        </div>
    </form>
</div>
</body>
</html>
