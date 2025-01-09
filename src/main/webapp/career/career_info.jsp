<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.dto.CareerStepGetDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.dto.CareerStepGetDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Карьера</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="form-container, general-div">
    <div class="item-header">
        <h2>Послужной список сотрудника</h2>
    </div>
    <%
        List<CareerStepGetDTO> career = (List<CareerStepGetDTO>) request.getAttribute(ConstantParamAndAttribute.CAREER);
        if (career == null || career.isEmpty()) {
    %>
    <div class="form-group>
        <h3>Информация о карьере сотрудника отсутствует</h3>
    </div>
    <% } else { %>
    <div class="form-group">
        <table>
            <tr>
                <th>Дата назначения</th>
                <th>Приказ о назначении</th>
                <th>Должность</th>
                <th colspan="2">Действие</th>
            </tr>
            <tr>
                <% for (CareerStepGetDTO careerStep : career) { %>
                <td><%=careerStep.getId().getDateOfAppointment()%>
                </td>
                <td><%=careerStep.getOrder()%>
                </td>
                <td><%=careerStep.getPositionFullName()%>
                </td>
                <td>
                    <form name="update_career_step"
                          method="get"
                          action="<%= ConstantAction.UPDATE_CAREER_STEP %>">
                        <button class="button-edit">
                            Изменить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%=careerStep.getId().getEmployee()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.POSITION_ID%>"
                               value="<%=careerStep.getId().getPosition()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DATE_OF_APPOINTMENT%>"
                               value="<%=careerStep.getId().getDateOfAppointment()%>"/>
                    </form>
                </td>
                <td>
                    <form name="delete_career_step"
                          method="post"
                          action="<%= ConstantAction.DELETE_CAREER_STEP %>">
                        <button class="button-delete">
                            Удалить
                        </button>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                               value="<%=careerStep.getId().getEmployee()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.POSITION_ID%>"
                               value="<%=careerStep.getId().getPosition()%>"/>
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.DATE_OF_APPOINTMENT%>"
                               value="<%=careerStep.getId().getDateOfAppointment()%>"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
    <% } %>
    <div class="tabs">
        <form name="employee"
              method="get"
              action="<%= ConstantAction.EMPLOYEE %>">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.ID%>"
                    value="<%= request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру сотрудника
            </button>
        </form>
    </div>
</div>
</body>
</html>
